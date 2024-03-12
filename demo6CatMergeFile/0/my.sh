#!/bin/bash

# 定义输出文件
output_file="merged_file.txt"

# 清空输出文件，如果它已经存在
> "$output_file"

# 循环遍历所有要合并的文件
for file in *.txt; do
    # 检查文件是否存在
    if [ -f "$file" ]; then
        # 将文件内容追加到输出文件
        cat "$file" >> "$output_file"
        # 检查文件最后一行是否以换行符结束，如果不是，则添加一个
        tail -c1 "$file" | read -r _ || echo >> "$output_file"
    else
        echo "Warning: '$file' not found."
    fi
done
