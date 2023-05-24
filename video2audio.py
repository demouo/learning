# _*_ coding : utf-8 _*_
# @Time : 2023/5/11 20:25
# @Author : momo
# @File : video2audio
# @Project : py_one2one
import os
import time

'''使用方法
1. 直接执行 输入视频路径
2. 正确输入音频格式
'''

'''优化输出
1. 只需调整一个输出函数 write_audiofile
2. bitrate 决定质量
3. nbytes 是采样宽度
'''

def flv2mp3(srcPath, tgtPath,save_dir):
    import moviepy.editor as mp
    # 音质可选 50K 500K 3000K
    bitrate='3000K'
    start = time.time()
    print("[OPEN] ----正在执行video转换audio----")
    # 大文件夹
    srcPath=srcPath[:srcPath.rfind('\\')]
    # 保存路径
    print('[PATH] ' + save_dir)
    # 防止字符串乱码
    os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.UTF8'
    # 打开文件夹
    file_list = os.listdir(srcPath)
    last=tgtPath[tgtPath.rfind('.')+1:]
    for file in file_list:
        if "mp4" in file or 'flv' in file or 'avi' in file:
            name=file[:file.rfind('.')]
            clip = mp.AudioFileClip(os.path.join(srcPath, file))
            clip.write_audiofile(save_dir+'\\'+name+'.'+last, bitrate=bitrate)


    print("[SUSS] --video转换audio成功，请到”" + save_dir + "“查收--")
    end = time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

if __name__ == "__main__":

    '''
    要修改保存的目录在此处
    '''
    save_dir = 'D:\\you-get\\音频\\'
    ok_last = [
        'wav','flv', 'mp4', 'mp3'
    ]
    while True:
        # 源文件路径
        src_file = str(input("请输入源文件路径(输入q退出)："))
        if (src_file == 'q'): break
        if (src_file[0] == '"'):
            src_file = src_file[1:-1]
        src_last = src_file[src_file.rfind('.') + 1:]
        if (src_last not in ok_last):
            print("此文件暂时无法转换，请检查文件后缀名")
            continue
        # 后缀
        tgt_last = str(input("要转成的文件后缀为："))
        # 如果文件是合适的格式的话 会进行文件解析并生成
        tgt_file = save_dir[:-1]
        if (tgt_last in ok_last):
            # 文件名
            left = src_file.rfind('\\')
            right = src_file.rfind('.')
            target_name = src_file[left + 1:right] + '.' + tgt_last
            # 创建文件
            tgt_file = save_dir + target_name

            flv2mp3(src_file, tgt_file, save_dir)
        else:
            print('暂不支持转换为该类型，请检查文件后缀名')
            continue


