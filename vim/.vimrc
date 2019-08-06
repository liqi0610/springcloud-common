set number
syntax on
colorscheme koehler
inoremap jj <Esc>`^
let mapleader=','
let g:mapleader=','

inoremap <leader>w <Esc>:W<cr>
noremap <leader>w :w<cr>
" use Control+h/j/k/l switch windows
noremap <C-h> <C-w>h
noremap <C-j> <C-w>j
noremap <C-k> <C-w>k
noremap <C-l> <C-w>l
" Specify a directory for plugins
" - For Neovim: ~/.local/share/nvim/plugged
" - Avoid using standard Vim directory names like 'plugin'
call plug#begin('~/.vim/plugged')
Plug 'mhinz/vim-startify'
Plug 'scrooloose/nerdtree'
" Initialize plugin system
call plug#end()

nnoremap <leader>v :NERDTreeFind<cr>
nnoremap <leader>g :NERDTreeToggle<cr>
let NERDTreeShowHidden=1
let NERDTreeIgnore = [
        \ '\.git$','\.hg$','\.svn$','\.stversions$','\.pyc$'
        \]