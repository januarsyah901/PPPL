#!/bin/bash
export PERL5LIB="/Users/mrfrog/perl5/lib/perl5:$PERL5LIB"
exec /Library/TeX/texbin/latexindent "$@"
