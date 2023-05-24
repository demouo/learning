# _*_ coding : utf-8 _*_
# @Time : 2023/5/11 9:32
# @Author : momo
# @File : file2file
# @Project : py_one2one
import os
import time

# 示范性函数
def pdf2img(srcPath,tgtPath,save_dir):

    from pdf2image import convert_from_path

    start=time.time()
    print("[OPEN] ----正在执行pdf转换img----")
    # 500是像素
    pages = convert_from_path(srcPath, 100)
    # 文件名
    left = srcPath.rfind('\\')
    right = srcPath.rfind('.')
    name = srcPath[left + 1:right]
    # 保存路径
    save_path=save_dir+'img'
    print('[PATH] ' + save_path)
    #创建收集的目录
    if not os.path.exists(save_path):
       os.mkdir(save_path)
    # 多图保存
    for index, img in enumerate(pages):
        img.save(save_path+'\\'+name+'_page_%s.jpg' % (index + 1))
    print("[SUSS] --pdf转换img成功，请到”"+save_path+"“查收--")
    end = time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

# 示范且批量（针对于同一个文件夹下的所有图片）
def img2pdf(srcPath, tgtPath,save_dir):
    from PIL import Image
    import os

    start=time.time()
    print("[OPEN] ----正在执行img转换pdf----")
    # 大文件夹
    srcPath=srcPath[:srcPath.rfind('\\')]
    # 保存路径
    save_path = save_dir + 'pdf'
    print('[PATH] ' + save_path)
    # 创建收集的目录
    if not os.path.exists(save_path):
        os.mkdir(save_path)
    # 防止字符串乱码
    os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.UTF8'
    # 打开文件夹
    file_list = os.listdir(srcPath)
    for file in file_list:
        if "jpg" in file or 'png' in file or 'jpeg' in file:
            name=file[:file.rfind('.')]
            im1 = Image.open(os.path.join(srcPath, file))
            im1.save(save_path+'\\'+name+'.pdf', "PDF", resolution=100.0)
    print("[SUSS] --img转换pdf成功，请到“"+save_path+"”查收--")
    end = time.time()
    print('[TIME] '+str(round(end-start,3))+'s\n')
def pdf2word(srcPath,tgtPath,save_dir):
    # pdf2word
    from pdf2docx import Converter

    start=time.time()
    # 执行转换
    print("[OPEN] ----正在执行pdf转换word----")
    cv = Converter(srcPath)
    print('[PATH] ' + tgtPath)
    cv.convert(tgtPath, start=0, end=None)
    cv.close()
    print("[SUSS] --pdf转换word成功，请到”"+save_dir+"“查收--")
    end = time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

def word2pdf(srcPath, tgtPath,save_dir):
    # word2pdf
    from win32com.client import constants, gencache

    start=time.time()
    print("[OPEN] ----正在执行word转换pdf----")
    word = gencache.EnsureDispatch('Word.Application')
    doc = word.Documents.Open(srcPath, ReadOnly=1)
    print('[PATH] ' + tgtPath)
    doc.ExportAsFixedFormat(tgtPath,
                            constants.wdExportFormatPDF,
                            Item=constants.wdExportDocumentWithMarkup,
                            CreateBookmarks=constants.wdExportCreateHeadingBookmarks)
    word.Quit(constants.wdDoNotSaveChanges)
    print("[SUSS] --word转换pdf成功，请到“"+save_dir+"”查收--")
    end = time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

def exl2pdf(srcPath, tgtPath,save_dir):
    # Import Module
    from win32com.client import DispatchEx

    start=time.time()
    print("[OPEN] ----正在执行excel转换pdf----")
    xl_app = DispatchEx("Excel.Application")
    xl_app.Visible = False
    xl_app.DisplayAlerts = 0
    books = xl_app.Workbooks.Open(srcPath, False)
    for sh in books.Sheets:
        sh.PageSetup.Orientation =1
        sh.PageSetup.Zoom = False
        sh.PageSetup.FitToPagesWide=1
    print('[PATH] ' + tgtPath)
    books.ExportAsFixedFormat(0,tgtPath)
    books.Close(False)
    xl_app.Quit()
    print("[SUSS] --excel转换pdf成功，请到“"+save_dir+"”查收--")
    end = time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

def ppt2pdf(srcPath, tgtPath,save_dir,choice):
    import win32com.client

    start=time.time()
    tgt_last="pdf"
    # 如果转图片
    if(choice==17):
        tgt_last="img"
        tgtPath=save_dir+'img'
    print("[OPEN] ----正在执行ppt转换"+tgt_last+"----")
    # 2). 打开PPT程序
    ppt_app = win32com.client.Dispatch('PowerPoint.Application')
    # ppt_app.Visible = True # 程序操作应用程序的过程是否可视化
    ppt = ppt_app.Presentations.Open(srcPath)
    # 4). 打开的PPT另存为pdf文件。17数字是ppt转图片，32数字是ppt转pdf。
    print('[PATH] '+tgtPath)
    ppt.SaveAs(tgtPath, choice)
    # 退出PPT程序
    ppt_app.Quit()
    print("[SUSS] --ppt转换"+tgt_last+"成功，请到“"+save_dir+"”查收--")
    end=time.time()
    print('[TIME] ' + str(round(end - start, 3)) + 's\n')

if __name__ == '__main__':
    '''
    要修改保存的目录在此处（默认是桌面）
    '''
    save_dir = 'D:\\onedrive\\桌面\\'
    ok_last=[
        'doc','docx','pdf','ppt','pptx','xls','xlsx','png','jpg','jpeg','img'
    ]
    while True:
        # 源文件路径
        src_file = str(input("请输入源文件路径(输入q退出)："))
        if(src_file=='q'):break

        if (src_file[0] == '"'):
            src_file = src_file[1:-1]
        src_last = src_file[src_file.rfind('.') + 1:]
        if (src_last not in ok_last):
            print("此文件暂时无法转换，请检查文件后缀名")
            continue
        # 后缀
        tgt_last = str(input("要转成的文件后缀为："))
        # 默认保存在桌面
        # 如果文件是合适的格式的话 会进行文件解析并生成
        tgt_file = save_dir[:-1]
        if (tgt_last in ok_last):
            # 文件名
            left = src_file.rfind('\\')
            right = src_file.rfind('.')
            target_name = src_file[left + 1:right] + '.' + tgt_last
            # 创建文件
            tgt_file = save_dir + target_name
            file = open(tgt_file, 'w')
            file.close()
        else:
            print('暂不支持转换为该类型，请检查文件后缀名')
            continue

        # switch语句
        # word ->
        if (src_last == 'doc' or src_last == 'docx'):
            if (tgt_last == 'pdf'):
                word2pdf(src_file, tgt_file, save_dir)
            else:
                print("此转换暂时无法完成，请更换格式或联系管理员")
        # pdf ->
        elif (src_last == 'pdf'):
            if (tgt_last == 'doc' or tgt_last == 'docx'):
                pdf2word(src_file, tgt_file, save_dir)
            elif (tgt_last == 'img'):
                pdf2img(src_file, tgt_file, save_dir)
            else:
                print("此转换暂时无法完成，请更换格式或联系管理员111")
        # excel ->
        elif (src_last == 'xlsx' or src_last == 'xls'):
            if (tgt_last == 'pdf'):
                exl2pdf(src_file, tgt_file, save_dir)
            else:
                print("此转换暂时无法完成，请更换格式或联系管理员")
        # ppt ->
        elif (src_last == 'ppt' or src_last == 'pptx'):
            if (tgt_last == 'pdf'):
                ppt2pdf(src_file, tgt_file, save_dir, 32)
            elif (tgt_last == 'img'):
                ppt2pdf(src_file, tgt_file, save_dir, 17)
            else:
                print("此转换暂时无法完成，请更换格式或联系管理员")
        # img ->
        elif (src_last == 'png' or src_last == 'jpg' or src_last == 'jpeg' or src_last == 'img'):
            if (tgt_last == 'pdf'):
                img2pdf(src_file, tgt_file, save_dir)
            else:
                print("此转换暂时无法完成，请更换格式或联系管理员")
        # future
        else:
            print("文件君正在赶来的路上，请重试......")
