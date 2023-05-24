# _*_ coding : utf-8 _*_
# @Time : 2023/5/24 9:14
# @Author : momo
# @File : img_3
# @Project : qushuiyin

from itertools import product
from PIL import Image

img = Image.open('src_img/img_9.png')
width, height = img.size
for pos in product(range(width), range(height)):
    if sum(img.getpixel(pos)[:3]) > 600:
        img.putpixel(pos, (255, 255, 255))
img.save('src_img/removed_1.png')



