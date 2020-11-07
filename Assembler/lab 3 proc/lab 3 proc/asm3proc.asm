.386
PUBLIC nbrCntStdCall
PUBLIC @nbrCntFastCall@12
PUBLIC nbrCntCdecl
.model flat
.data
	newlen dd ?
	narr dd ?
.code

;*******************************************************************************************

nbrCntStdCall proc stdcall, arr:dword, len:dword, newarr:dword
		PUSH ebp
		MOV newlen, 0
		MOV ebx, arr
		MOV ecx, len
	
 		DEC ecx
		DEC ecx

		cikl: 
			MOV eax, [ebx + ecx * 4  + 4]		;+1-�� ������� � eax
			ADD eax, [ebx + ecx * 4 - 4]		;��������� n-1-�� �������
			CDQ
			SUB eax, edx						;������� �� 2 ��� ������ ������
			SAR eax, 1
			CMP eax, [ebx + ecx * 4]			;�������� � n-1 ���������
			JE _add
			LOOP cikl
			JMP _end

		_add:
			PUSH ebx
			MOV ebx, newarr
			PUSH ecx
			MOV ecx, newlen						;���������� ��������� ������ ������� � ecx
			CMP ecx,0							;���� �� ���, ������������� � write
			JE write
				CheckCikl:	
					CMP eax, [ebx + ecx * 4 - 4]		;���������� ���������� �� ����� ���������� ������ �������
					JE nowrite									;� ���� �����, ������������ � cikl
					LOOP CheckCikl
					
		write: 
			MOV ecx, newlen
			MOV ebx, newarr
			MOV [ebx + ecx * 4], eax
			INC ecx
			MOV newlen, ecx
		nowrite:
			POP ecx
			POP ebx
			LOOP cikl
	_end:
	mov eax, newlen
	ret 12
nbrCntStdCall endp

;**********************************************************************************************************************************

@nbrCntFastCall@12 proc
		PUSH ebp
		MOV newlen, 0
		MOV ebx, ecx
		MOV ecx, edx
		MOV ebp, esp
		MOV edx, [ebp+8]
		MOV narr,edx
	
 		DEC ecx
		DEC ecx

		cikl: 
			MOV eax, [ebx + ecx * 4  + 4]		;+1-�� ������� � eax
			ADD eax, [ebx + ecx * 4 - 4]		;��������� n-1-�� �������
			CDQ
			SUB eax, edx						;������� �� 2 ��� ������ ������
			SAR eax, 1
			CMP eax, [ebx + ecx * 4]			;�������� � n-1 ���������
			JE _add
			LOOP cikl
			JMP _end

		_add:
			PUSH ebx
			MOV ebx, narr
			PUSH ecx
			MOV ecx, newlen						;���������� ��������� ������ ������� � ecx
			CMP ecx,0							;���� �� ���, ������������� � write
			JE write
				CheckCikl:	
					CMP eax, [ebx + ecx * 4 - 4]		;���������� ���������� �� ����� ���������� ������ �������
					JE nowrite									;� ���� �����, ������������ � cikl
					LOOP CheckCikl
					
		write: 
			MOV ecx, newlen
			MOV ebx, narr
			MOV [ebx + ecx * 4], eax
			INC ecx
			MOV newlen, ecx
		nowrite:
			POP ecx
			POP ebx
			LOOP cikl
	_end:
	POP ebp
	MOV eax, newlen	
	ret 4
@nbrCntFastCall@12 endp

;****************************************************************************************************************

nbrCntCdecl proc C, arr:dword, len:dword, newarr:dword
		PUSH ebp
		MOV newlen, 0
		MOV ebx, arr
		MOV ecx, len
	
 		DEC ecx
		DEC ecx

		cikl: 
			MOV eax, [ebx + ecx * 4  + 4]		;+1-�� ������� � eax
			ADD eax, [ebx + ecx * 4 - 4]		;��������� n-1-�� �������
			CDQ
			SUB eax, edx						;������� �� 2 ��� ������ ������
			SAR eax, 1
			CMP eax, [ebx + ecx * 4]			;�������� � n-1 ���������
			JE _add
			LOOP cikl
			JMP _end

		_add:
			PUSH ebx
			MOV ebx, newarr
			PUSH ecx
			MOV ecx, newlen						;���������� ��������� ������ ������� � ecx
			CMP ecx,0							;���� �� ���, ������������� � write
			JE write
				CheckCikl:	
					CMP eax, [ebx + ecx * 4 - 4]		;���������� ���������� �� ����� ���������� ������ �������
					JE nowrite									;� ���� �����, ������������ � cikl
					LOOP CheckCikl
					
		write: 
			MOV ecx, newlen
			MOV ebx, newarr
			MOV [ebx + ecx * 4], eax
			INC ecx
			MOV newlen, ecx
		nowrite:
			POP ecx
			POP ebx
			LOOP cikl
	_end:
	mov eax, newlen
	ret
nbrCntCdecl endp

;************************************************************************************************************************

end