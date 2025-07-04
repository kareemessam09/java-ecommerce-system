#!/bin/bash

# E-commerce System Compilation and Test Script
echo "=== E-COMMERCE SYSTEM COMPILATION ==="

# Clean and create build directory
echo "Cleaning build directory..."
rm -rf build
mkdir -p build

# Clean any stray .class files from source directories
find . -name "*.class" -not -path "./build/*" -delete

# Compile all Java files to build directory
echo "Compiling Java files..."
javac -cp . -d build interfaces/*.java models/*.java services/*.java tests/*.java Main.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "✓ All .class files are in build/ directory"
    echo ""
    echo "=== RUNNING E-COMMERCE SYSTEM TESTS ==="
    echo ""
    
    # Run the test from build directory with proper package name
    cd build
    java tests.test
    cd ..
else
    echo "✗ Compilation failed!"
    exit 1
fi
