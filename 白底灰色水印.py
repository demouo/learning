# _*_ coding : utf-8 _*_
# @Time : 2023/5/18 12:33
# @Author : momo
# @File : img_1
# @Project : qushuiyin


import cv2
import numpy as np

if __name__=='__main__':
    src = 'src_img/img_12.png'
    img = cv2.imread(src)
    cnt=0
    nums=160
    # -250是加深颜色  -190很合适  原来是-160有点模糊  -100除彩色
    while cnt<5:
        new = np.clip(2.0 * img - nums, 0, 255).astype(np.uint8)
        name = src[:src.find('.')]
        last = src[src.find('.') + 1:]
        cv2.imwrite(name + '_out_'+str(nums)+'.' + last, new)
        cnt+=1
        nums+=10