import numpy 
import VideoCapture as vc
from PIL import Image

if __name__ == "__main__":
    vc.Device().getImage().save("out.bmp")