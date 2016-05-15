#!/bin/bash

set -e

mkdir -p Washington_speech_files
cd Washington_speech_files
wget "https://depts.washington.edu/phonlab/resources/pnnc/pnnc-v1.tar.gz"
tar -xvzf pnnc-v1.tar.gz
rm pnnc-v1.tar.gz
