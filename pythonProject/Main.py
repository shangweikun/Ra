from lxml import etree
import os

def find_mapper_files(directory, extension=".mapper.xml"):
    matches = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(extension):
                matches.append(os.path.join(root, file))
    return matches

# 替换为你要搜索的目录路径
directory = "C:\\tmp0\\NCBS_AP\\NCBS_PARAM_CENTER"
# 调用函数并打印结果
mapper_files = find_mapper_files(directory)
for mapper_file in mapper_files:
    # 加载XML文件
    tree = etree.parse(mapper_file)
    root = tree.getroot()

    # 遍历XML文件中的所有"country"标签
    for country in root.findall('update'):
        # 获取"country"标签的"name"属性
        id = country.get('id')
        print(f'select Name: {id}')
        value = etree.tostring(country, pretty_print=True).decode()
        print(f'{value}')
