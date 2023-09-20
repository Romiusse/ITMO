section .text
 
SYSCALL_EXIT equ 60
SYSCALL_READ equ 0
SYSCALL_WRITE equ 1
STDIN equ 0
STDOUT equ 1
SIGN_CHECK equ 0x8000_0000_0000_0000

; Принимает код возврата и завершает текущий процесс
exit: 
    mov rax, SYSCALL_EXIT,
    syscall 

; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
    xor rax, rax
.startWhileSL:
    mov cl, [rdi + rax] ;Count and cmp each char at str
    test cl, cl
    jz .endWhileSL
    inc rax
    jmp .startWhileSL
.endWhileSL:
    ret

; Принимает указатель на нуль-терминированную строку, выводит её в stdout
print_string:
    xor rax, rax
    push rdi    ; Save string pointer
    call string_length
    pop rsi     ;Recover string pointer
    mov rdx, rax
    xor rax, rax
    mov rax, SYSCALL_WRITE
    mov rdi, STDOUT
    syscall
    ret

; Переводит строку (выводит символ с кодом 0xA)
print_newline:
    xor rax, rax
    mov rdi, `\n`

; Принимает код символа и выводит его в stdout
print_char:
    xor rax, rax
    push rdi
    mov rax, SYSCALL_WRITE
    mov rsi, rsp
    mov rdi, STDOUT
    mov rdx, 1
    syscall
    pop rdi

    ret

; Выводит знаковое 8-байтовое число в десятичном формате 
print_int:
    xor rax, rax
    mov rcx, SIGN_CHECK      ;Check sign
    and rcx, rdi
    test rcx, rcx    
    jz print_uint
    neg rdi                  ; If < 0
    push rdi
    mov rdi, '-'
    call print_char
    pop rdi

; Выводит беззнаковое 8-байтовое число в десятичном формате 
; Совет: выделите место в стеке и храните там результаты деления
; Не забудьте перевести цифры в их ASCII коды.
print_uint:

    push rbx
    xor rax, rax
    xor rcx, rcx
    sub rsp, 21             ; Resolve 21 sumbols in stack
    lea rcx, [rsp + 20]
    mov byte [rcx], 0       ; Add 0 (str end)
.startWhilePU:
    dec rcx 
    xor rdx, rdx
    mov rax, rdi
    mov rbx, 10             ; Div 10
    div rbx
    add rdx, '0'            ; Make char
    mov byte [rcx], dl      ; Add to stack cur char
    test rax, rax
    jz .startWhilePUP
    xor rdi, rdi
    mov rdi, rax
    jmp .startWhilePU
.startWhilePUP:
    lea rdi, [rcx]
    call print_string
    add rsp, 21

    pop rbx
    ret


; Принимает два указателя на нуль-терминированные строки, возвращает 1 если они равны, 0 иначе
string_equals:
    push r15
    push r14
    
    xor rax, rax

    push rdi
    push rsi
    call string_length
    pop rsi
    pop rdi

    mov rcx, rax
    mov rdi, rsi
    push rcx
    push rdi
    push rsi
    call string_length
    pop rsi
    pop rdi
    pop rcx

    cmp rcx, rax
    jz .okSE
.notokSE:
    mov rax, 0
    jmp .retSE
.okSE:
    xor rax, rax
    jmp .cntSE
.stWhileSE:
    mov r15b, [rdi + rax]
    mov r14b, [rdi + rax]
    cmp r15b, r14b
    jne .notokSE
    inc rax
.cntSE:    
    cmp rax, rcx
    jne .stWhileSE
    mov rax, 1
.retSE:
    pop r14
    pop r15
    ret

; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char:
    mov rax, SYSCALL_READ
    mov rdi, STDIN
    xor rcx, rcx
    push rcx
    mov rsi, rsp
    mov rdx, 1
    syscall

    test rax, rax
    pop rax
    jne .endStreamRC
    xor rax, rax
.endStreamRC:
    ret 

; Принимает: адрес начала буфера, размер буфера
; Читает в буфер слово из stdin, пропуская пробельные символы в начале, .
; Пробельные символы это пробел 0x20, табуляция 0x9 и перевод строки 0xA.
; Останавливается и возвращает 0 если слово слишком большое для буфера
; При успехе возвращает адрес буфера в rax, длину слова в rdx.
; При неудаче возвращает 0 в rax
; Эта функция должна дописывать к слову нуль-терминатор

read_word:
    push r15
    push r14
    push r13
    push r12

    xor r15, r15    ;is spaces was flag
    xor rax, rax
    xor rcx, rcx    ;cur buff len

.startWhileRW:

    cmp rcx, rsi
    jz .bufOvRW

    mov r14, rdi
    mov r13, rsi
    mov r12, rcx
    call read_char
    mov rcx, r12
    mov rsi, r13
    mov rdi, r14

    test rax, rax
    jz .endStrRW

    cmp rax, ' '
    jz .contCompRW
    cmp rax, `\t`
    jz .contCompRW
    cmp rax, `\n`
    jz .contCompRW

    mov byte [rdi + rcx], al
    inc rcx
    mov r15, 1
    jmp .startWhileRW
.contCompRW:
    cmp r15, 1
    jz .endStrRW
    jmp .startWhileRW
.bufOvRW:
    mov rax, 0
    jmp .returnRW
.endStrRW:
    mov byte [rdi + rcx], 0
    mov rax, rdi
    mov rdx, rcx
    jmp .returnRW
.returnRW:
    pop r12
    pop r13
    pop r14
    pop r15
    ret
 

; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
parse_uint:
    push r15
    push r14

    xor rax, rax
    xor rdx, rdx
    xor rcx, rcx
    xor r14, r14
    xor r15, r15

.startWhilePaU:

    mov r15b, [rdi + rcx]

    test r15b, r15b            ; if end of line
    jz .returnPaU            ; then return

    cmp r15b, '9'           ; if r15b > '9'
    ja .continuePaU         ;                => then continue
    cmp r15b, '0'           ; if r15b < '0'
    jb .continuePaU
    

    sub r15b, '0'           ; convert char to digit

    mov rdx, 10             
    mul rdx                 ; pow 10 
    add rax, r15            ; add digit to end
    inc r14                 ; inc num len

.continuePaU:
    inc rcx                 ; inc cur char ptr
    jmp .startWhilePaU

.returnPaU:
    mov rdx, r14
    pop r14
    pop r15
    ret

; Принимает указатель на строку, пытается
; прочитать из её начала знаковое число.
; Если есть знак, пробелы между ним и числом не разрешены.
; Возвращает в rax: число, rdx : его длину в символах (включая знак, если он был) 
; rdx = 0 если число прочитать не удалось
parse_int:
    xor rax, rax
    xor rcx, rcx

    mov cl, [rdi]
    push rcx
    call parse_uint
    pop rcx
    cmp cl, '-'
    jne .returnPaI
    neg rax
    inc rdx
.returnPaI:
    ret 

; Принимает указатель на строку, указатель на буфер и длину буфера
; Копирует строку в буфер
; Возвращает длину строки если она умещается в буфер, иначе 0
string_copy:
    push r15
    push rsi
    xor rax, rax
    xor rcx, rcx
    xor r15, r15

.startWhileSC:

    cmp rcx, rdx
    jz .bufOvSC

    mov r15b, [rdi + rcx]
    mov byte [rsi + rcx], r15b

    test r15b, r15b
    jz .returnSC

    inc rcx
    jmp .startWhileSC

.bufOvSC:
    mov rax, 0
    jmp .returnSC
.endStrSc:
    mov rax, rcx
    jmp .returnSC
.returnSC:
    pop rsi
    pop r15
    ret
