# _*_ coding : utf-8 _*_
# @Time : 2023/5/17 9:08
# @Author : momo
# @File : n_1
# @Project : py_one2one
import base64
import codecs
import json
import math
import os
import random
import time

'''使用简介
1. 搜索歌曲
2. 选择下载第一首还是一整列
'''

'''实现
1. 解密netease的搜索界面（使用了html嵌套，无法xpath）
2. 最初的爬虫办法，找到是谁在返回数据
3. 发现加密算法 模拟加密算法的全程实现
4. 获得并封装参数，发送post请求
'''

import requests
from Cryptodome.Cipher import AES

e = '010001'
f = '00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7'
g = '0CoJUm6Qyw8W8jud'


def generate_str(lenght):
    str = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'
    res = ''
    for i in range(lenght):
        index = random.random() * len(str)  # 获取一个字符串长度的随机数
        index = math.floor(index)  # 向下取整
        res = res + str[index]  # 累加成一个随机字符串
    return res

# AES加密获得params
def AES_encrypt(text, key):
    iv = '0102030405060708'.encode('utf-8')  # iv偏移量
    text = text.encode('utf-8')  # 将明文转换为utf-8格式
    pad = 16 - len(text) % 16
    text = text + (pad * chr(pad)).encode('utf-8')  # 明文需要转成二进制，且可以被16整除
    key = key.encode('utf-8')  # 将密钥转换为utf-8格式
    encryptor = AES.new(key, AES.MODE_CBC, iv)  # 创建一个AES对象
    encrypt_text = encryptor.encrypt(text)  # 加密
    encrypt_text = base64.b64encode(encrypt_text)  # base4编码转换为byte字符串
    return encrypt_text.decode('utf-8')

# RSA加密获得encSeckey
def RSA_encrypt(str, key, f):
    str = str[::-1]  # 随机字符串逆序排列
    str = bytes(str, 'utf-8')  # 将随机字符串转换为byte类型的数据
    sec_key = int(codecs.encode(str, encoding='hex'), 16) ** int(key, 16) % int(f, 16)  # RSA加密
    return format(sec_key, 'x').zfill(256)  # RSA加密后字符串长度为256，不足的补x

def get_params(d, e, f, g):
    i = generate_str(16)    # 生成一个16位的随机字符串
    # i = 'aO6mqZksdJbqUygP'
    encText = AES_encrypt(d, g)
    # print(encText)    # 打印第一次加密的params，用于测试d正确
    params = AES_encrypt(encText, i)  # AES加密两次后获得params
    encSecKey = RSA_encrypt(i, e, f)  # RSA加密后获得encSecKey
    return params, encSecKey

def get_data(msg, url):
    encText, encSecKey = get_params(msg, e, f, g)   # 获取参数
    params = {
        "params": encText,
        "encSecKey": encSecKey
    }
    re = requests.post(url=url, params=params, verify=False)    # 向服务器发送请求
    return re.json()    #返回结果


if __name__=='__main__':
    while True:
        valid_nums=0
        searchfor_list = str(input("请输入搜索内容(多个内容请按[分号;]分割，按q退出)："))
        if searchfor_list=='q':break
        start=time.time()
        searchfor_list=searchfor_list.strip()
        if_download_list=str(input("要下载整个搜索列表吗？（如下载歌手的全部歌曲，[y]/n）："))
        searchfor_list=searchfor_list.split('；')
        print('搜索数量： '+str(len(searchfor_list)))
        for searchfor in searchfor_list:
            # 搜索有效
            if searchfor=='':continue
            # 搜索内容判别
            if not searchfor.isspace() :
                sub_valid_nums=0
                d = r'{"hlpretag":"<span class=\"s-fc7\">","hlposttag":"</span>","s":"' + searchfor + '","type":"1","offset":"0","total":"true","limit":"30","csrf_token":""}'
                url = 'https://music.163.com/weapi/cloudsearch/get/web?csrf_token='
                json_d = json.dumps(get_data(d, url), ensure_ascii=False)
                json_l = json.loads(json_d)
                song_list = json_l['result']['songs']
                print('搜索内容： ' + searchfor)
                for i in range(len(song_list)):
                    song_name = song_list[i]['name']
                    song_id = song_list[i]['id']
                    auth_list = song_list[i]['ar']
                    auth_name_list = []
                    sub_auth_name=''
                    for j in range(len(auth_list)):
                        auth_name_list.append(auth_list[j]['name'])
                        sub_auth_name+=auth_list[j]['name']+','
                    # print('歌名：' + str(song_name) + '\n歌曲id：' + str(song_id) + '\n作者名：' + str(
                    #     auth_name_list))
                    print('歌名：' + str(song_name)+ '\n作者名：' + str(
                        auth_name_list))
                    song_info=song_name+'-'+sub_auth_name[:-1]
                    url='http://music.163.com/song/media/outer/url?id='+str(song_id)+'.mp3'
                    command = 'yt-dlp -o "D:\\yt-dlp\\音频\\%s.mp3" %s' % (song_info, url)
                    print('[COMM] 命令行：' + command)
                    print('[LOAD] 正在执行yt-dlp')
                    code = os.system(command)
                    if code == 0:
                        sub_valid_nums += 1
                        print('[SUSS] 恭喜你， “' + song_info + '” 下载成功')
                        print('')
                    if if_download_list=='n':break
                print('[SEND] 单次搜索结束，成功下载'+str(sub_valid_nums)+'首\n')
                valid_nums+=sub_valid_nums
                # 存档
                # with open('./json_load/' + searchfor + '.json', 'w', encoding='utf-8') as fp:
                #     fp.write(json_d)
        print('[SEAR] 搜索列表：'+str(searchfor_list))
        print('[AEND] 全部搜索结束，共下载'+str(valid_nums)+'首')
        print('[TIME] 总耗时:'+str(round(time.time()-start,3))+'s')

           