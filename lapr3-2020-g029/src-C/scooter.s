.section .text
    .global calculateChargeTime
calculateChargeTime:
    # prologue
    pushl %ebp # save previous stack frame pointer
    movl %esp, %ebp # the stack frame pointer for sum function
    pushl %ebx
    pushl %esi

    movl 8(%ebp), %eax
    movl 12(%ebp), %ebx
    movl 16(%ebp), %ecx
    
    subl %ebx, %eax
    cdq
    idivl %ecx
    
    popl %esi
    popl %ebx
    movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
    popl %ebp # restore the previous stack frame pointer
    ret
