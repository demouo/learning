# _*_ coding : utf-8 _*_
# @Time : 2023/5/19 11:15
# @Author : momo
# @File : video_
# @Project : qushuiyin
import os

'''使用方法
1. 输入 src output 的路径
2. 弹出图片框框 选择水印的位置
3. 务必注意：选出水印的左上角和右下角即可
4. 每次只能除一个水印，如需多个请多次使用本程序
'''

def on_EVENT_LBUTTONDOWN(event, x, y, flags, param):
    if event == cv2.EVENT_LBUTTONDOWN:
        xy = "%d,%d" % (x, y)
        a.append(x)
        b.append(y)
        cv2.putText(img, xy, (x, y), cv2.FONT_HERSHEY_PLAIN,
                    1.0, (0, 0, 0), thickness=2)
        cv2.imshow("image", img)

if __name__=='__main__':
    src_path=str(input('src='))
    if '"' in src_path:
        src_path=src_path[1:-1]
    output_path=str(input('output='))
    if '"' in output_path:
        output_path=output_path[1:-1]
    # 对视频截图，找到水印的位置
    comm1='ffmpeg -i '+src_path+' -y -f image2 -t 0.001 jietu.jpg'
    code=os.system(comm1)
    # 截图成功
    if code==0:
        # 调取图片
        import cv2
        img = cv2.imread('./jietu.jpg')
        a = []
        b = []
        cv2.namedWindow("image")
        cv2.setMouseCallback("image", on_EVENT_LBUTTONDOWN)
        cv2.imshow("image", img)
        cv2.waitKey(0)
        # 删除截图
        os.remove('./jietu.jpg')
        # 选了两个点就行 而且单次只能去一个水印
        if len(a)>1:
            # show=1显示水印绿框 0不显示 现在是机器选了 不用显示了
            show = 0
            x = a[0]
            y = b[0]
            w = a[1] - a[0]
            h = b[1] - b[0]

            command = 'ffmpeg -i "' + src_path + '" -filter_complex "delogo=x=' + str(x) + ':y=' + str(y) + ':w=' + str(
                w) + ':h=' + str(h) + ':show=' + str(show) + '" "' + output_path + '"'
            print(command)
            os.system(command)
        if len(a)<2:
            print('选取点少于两个，无法识别水印区域！')
        else:
            print('水印去除成功，感谢使用！')


