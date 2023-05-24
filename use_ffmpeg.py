# _*_ coding : utf-8 _*_
# @Time : 2023/5/22 22:32
# @Author : momo
# @File : use_ffmpeg
# @Project : py_one2one


import os

def v2a(path,s_path):
    files=os.listdir(path)
    for f in files:
        if 'mp4'in f or 'flv' in f:
            name=f.title()[:f.find('.')]
            f_path=os.path.join(path,f)
            save_path=s_path+'\\'+name+'.mp3'
            os.system('ffmpeg -i '+f_path+' '+save_path)

if __name__=='__main__':
    path=str(input('请输入文件夹路径：'))
    s_path='D:\\you-get\\音频'
    v2a(path,s_path)
