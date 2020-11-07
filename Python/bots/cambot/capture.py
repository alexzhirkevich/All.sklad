import numpy as np
import cv2
from threading import Thread
from PIL import Image

class Capture:

    def __init__(self, device=0):
        self.device = device
        self.busy = False


    def screen(self,size=(1280,720)):
        self.busy = True
        cap = cv2.VideoCapture(self.device,cv2.CAP_DSHOW)
        img =  Image.fromarray(cv2.cvtColor(cap.read()[1],cv2.COLOR_RGB2BGR))
        cap.release()
        self.busy = False
        return img

    def clip_async(self,sec):
        th = Thread(target = self.clip,args=(sec,))
        th.start()
        return th

    def clip(self,fname,sec,fps=24,res=(640,480)):
        self.busy = True
        out = cv2.VideoWriter(fname, cv2.VideoWriter_fourcc(*'mp4v'), fps, res)
        cap = cv2.VideoCapture(self.device,cv2.CAP_DSHOW)
        for i in range(sec*fps):
            ret, frame = cap.read()
            if ret==True:
                out.write(frame)
            else:
                break
        out.release()
        cap.release()
        self.busy = False
