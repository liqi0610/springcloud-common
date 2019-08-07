" 设置显示行号
set number
" 设置语法高亮
syntax on

" 设置 jj切换到normel模式
inoremap jj <Esc>`^
" 设置leader 键
let mapleader=','
let g:mapleader=','

" 设置保存映射
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
" 主题插件
Plug 'sainnhe/edge'
" Initialize plugin system
call plug#end()

nnoremap <leader>v :NERDTreeFind<cr>
nnoremap <leader>g :NERDTreeToggle<cr>
let NERDTreeShowHidden=1
let NERDTreeIgnore = [
        \ '\.git$','\.hg$','\.svn$','\.stversions$','\.pyc$'
        \]

" 设置主题
set termguicolors

" for dark version
set background=dark
colorscheme edge