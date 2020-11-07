.486
PUBLIC DelCopies
.model flat
.data
	pred db 0
	sogl db '¡·¬‚√„ƒ‰∆Ê«Á ÍÀÎÃÏÕÌœÔ–—Ò“Ú‘Ù’ı÷ˆ◊˜ÿ¯Ÿ˘⁄˙‹¸',0
	nstr db 256	dup (0)
	newlen dd 0
.code
DelCopies proc C, ctr:byte
	PUSH ebp
			LEA ebx, ctr								
			XOR ah, ah									
			XOR edx, edx								 
			_cikl :	
				MOV al, 0
				CMP [ebx + edx], al						
				JE _end									
				MOV ecx, 44
				_comp :
					MOV al, byte ptr sogl[ecx]
					CMP [ebx + edx] ,al
					JE _sum
					LOOP _comp
					CMP ah, 0
					JE _push
					DEC edx
					JMP _write
				_sum:
					CMP ah, 0
					JE _add
					MOV al, pred
					CMP [ebx+edx], al
					JNE _write
				_add:
					MOV al, [ebx+edx]
					MOV pred, al
					INC ah
					INC edx
					JMP _cikl
				_write:
					CMP ah,1
					JG _write2
					MOV ecx, newlen
					MOV al, pred
					MOV nstr[ecx], al
					XOR ah,ah
					INC ecx
					MOV newlen, ecx
					MOV al, 0
					CMP[ebx + edx], al
					JE _end
					MOV al, pred
					CMP[ebx + edx], al
					JNE _back
					INC edx
					JMP _cikl
				_write2:
					JMP _chislo1
				_backToWrite:
					XOR ah,ah
					INC ecx
					MOV newlen, ecx
					MOV al, 0
					CMP [ebx + edx], al
					JE _end
					MOV al, pred
					CMP[ebx + edx], al
					JNE _back
					INC edx
					JMP _cikl
				_back:
					MOV pred, 0
					JMP _cikl
				_push:
					MOV ecx,newlen
					MOV al, [ebx+edx]
					MOV nstr[ecx],al
					INC ecx
					MOV newlen, ecx
					INC edx
					JMP _cikl
			_end:
				XOR ecx, ecx
				CMP ah,0
				JG _write
			_endd:
				MOV ecx, newlen
				MOV nstr[ecx], 0
				INC ecx
			_endcikl:
				CMP ecx, 0
				JE _end2
				MOV al, nstr[ecx-1]
				MOV [ebx+ecx-1], al
				DEC ecx
				JMP _endcikl

			_chislo1:
				MOV ecx, newlen
				CMP ah,	10
				JGE _chislo2
				MOV al, '0'
				ADD al, ah
				MOV nstr[ecx], al
				JMP _backToWrite
			_chislo2:
				CMP ah, 100
				JGE _chislo3
				PUSH ebx
				PUSH edx
				MOV al,ah
				CBW
				MOV bl, 10
				DIV bl
				ADD al, '0'
				ADD ah, '0'
				MOV nstr[ecx],al
				INC ecx
				MOV nstr[ecx], ah
				POP edx
				POP ebx
				JMP _backToWrite
			_chislo3:
				PUSH ebx
				PUSH edx
				MOV al,ah
				CBW
				MOV bl, 10
				DIV bl
				ADD ah, '0'
				MOV nstr[ecx+2], ah
				CBW
				DIV bl
				ADD al, '0'
				ADD ah, '0'
				MOV nstr[ecx], al
				INC ecx
				MOV nstr[ecx], ah
				INC ecx
				POP edx
				POP ebx
				JMP _backToWrite
		_end2:
			MOV eax, newlen
		RET
	DelCopies endp
	end
