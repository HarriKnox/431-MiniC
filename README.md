# Minic - The MiniC Compiler

## TL;DR

    tar xf minic-src.tar.gz
    cd ./src
    make
    ./minic $FILE.mini
    gcc $FILE.s


## Building

To build this project, extract the tar file, cd into the src directory and run
`make`. This will compile the compiler and put the class files in an output
folder.


## Executing

You can execute minic (either by using a path to it `./minic` or by adding
it to your path; either will work). Pass to it the source file you want to
compile.

The source file must end in a ".mini" extension. If no files are provided or
the file argument is literally a hyphen ("-"), then Minic will read a source
file from STDIN and default output filenames to begin with "a" (such as a.out).


### Options

There is the `-stack` option that will use stack-based variable storage for
compiling the file. Omitting this option uses register-based variable storage.

There are two options to modify the output of the code: `-llvm` and `-arm`.
Each of those options has two variants. On their own, they each force Minic
to output the respective file with the default names for each file.

    minic -llvm brett.mini        # produces brett.ll
    minic -arm brett.mini         # produces brett.s
    minic -llvm -arm brett.mini   # produces both brett.ll and brett.s

You can use an equals sign to specify an output file. If you use just an equals
sign with no file, then the output is to stdout.

    minic -llvm= brett.mini          # dump the LLVM file to stdout
    minic -llvm=comp.ll brett.mini   # produces comp.ll with LLVM in it

There is also a `-clang` option to compile the LLVM code directly with clang,
but it will work only if you have Clang on your computer and I haven't tested
this one all that much. Use at your own risk.

## Examples

    minic brett.mini                      # produces brett.s
    minic -llvm brett.mini                # produces brett.ll
    minic -arm=arm.s brett.mini           # produces arm.s
    minic -llvm=LLVM.ll -arm brett.mini   # produces both LLVM.ll and brett.s
