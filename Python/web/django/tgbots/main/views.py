from django.shortcuts import render,redirect,get_object_or_404
from .forms import QRForm
from .models import QRCode
import pyqrcode
import time
import os
from PIL import Image

# Create your views here.

def index(request):
    if (request.method == "POST"):
        form = QRForm(data = request.POST,files=request.FILES)
        if (form.is_valid()):
            name = str(time.time()) + '.png'
            #code = pyqrcode.create(form.cleaned_data.get("text"),encoding='utf-8')
            #code.png(name,scale=8)
    else:
        form = QRForm()

    context = {
                'form': form
            }
    return render(request,"main/index.html",context)

def about(request):
    return render(request,About.url)



class About():
    url = "main/about.html"