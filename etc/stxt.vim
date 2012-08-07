" This file should be in ~/.vim/syntax/.
" Then add the following to your ~/.vimrc or ~/.gvimrc:
" 
"    augroup filetypedetect
"       au BufNewFile,BufRead *.stxt setf stxt
"    augroup END
" 
" Now, all STXT files that you open in VIM should be highlighted.

syntax clear

highlight link stxtHeader Comment
highlight link stxtKey SpecialComment
highlight link stxtHeading Type
highlight link stxtCode Function
highlight link stxtIndicator Delimiter
highlight link stxtIntraIndicator Delimiter
highlight link stxtLink Underlined
highlight link stxtBareLink Underlined
highlight link stxtEmail Underlined
highlight link stxtImage Underlined
highlight stxtItalic term=italic cterm=italic gui=italic
highlight stxtBold term=bold cterm=bold gui=bold
highlight link stxtMonospace Function
highlight link stxtSuper Function
highlight link stxtSub Function
highlight link stxtIgnore Special

syntax cluster stxtNestedInline contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtItalic,stxtBold,stxtMonospace,stxtSuper,stxtSub,stxtIgnore

syntax region stxtHeader start=/^+\{5,}/ end=/^+\{5,}/ contains=stxtKey
syntax match stxtKey /^\S\{-}:\(\s\)\@=/ contained
syntax region stxtHeading start=/^\s*=\+\s/ end=/$/ contains=@stxtNestedInline
syntax region stxtCode start=/^:\{2,}/ end=/^:\{2,}/ contains=stxtIgnore
syntax match stxtIndicator /^\s*\(>>\|;\|\*\|#\)\s/
syntax match stxtIntraIndicator / -- /
syntax region stxtItalic start=/\(^\|\W\)\@<=_\(\S\)\@=/ end=/\(\S_\)\@<=\($\|\W\)\@=/ contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtBold,stxtMonospace,stxtSuper,stxtSub,stxtIgnore
syntax region stxtBold start=/\(^\|\W\)\@<=\*\(\S\)\@=/ end=/\(\S\)\@<=\*\($\|\W\)\@=/ contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtItalic,stxtMonospace,stxtSuper,stxtSub,stxtIgnore
syntax region stxtMonospace start=/\(^\|\W\)\@<=:\([^: ]\)\@=/ end=/\(\S\)\@<=:\($\|\W\)\@=/ contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtItalic,stxtBold,stxtSuper,stxtSub,stxtIgnore
syntax region stxtSuper start=/\^\(\S\)\@=/ end=/\(\S\)\@<=\^/ contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtItalic,stxtBold,stxtMonospace,stxtSub,stxtIgnore
syntax region stxtSub start=/\~\(\S\)\@=/ end=/\(\S\)\@<=\~/ contains=stxtLink,stxtBareLink,stxtEmail,stxtImage,stxtItalic,stxtBold,stxtMonospace,stxtSuper,stxtIgnore

syntax region stxtLink start=/\[/ end=/\]/ contains=stxtImage,stxtItalic,stxtBold,stxtMonospace,stxtSuper,stxtSub,stxtIgnore
syntax region stxtImage start=/{/ end=/}/
syntax match stxtBareLink /\(http\|ftp\|afp\|smb\|gopher\):\/\/\(\w\|\.\|\/\|:\)*\(\w\|\/\)\+/
syntax match stxtEmail /\(mailto:\)\?\(\w\|\.\)\+@\(\w\|\.\)\+/

syntax match stxtIgnore /\\\(\\\|=\|\*\|_\|\^\|\~\|:\|{\|\[\)/

