from .models import QRCode
from django.forms import ModelForm, TextInput, ImageField

class QRForm(ModelForm):

    class Meta:
        model = QRCode
        fields =["text","img"]
        widgets = {
            "text": TextInput(attrs={
                'class':'form-control',
                'placeholder':'Текст qr-кода'
            }),
            "img": ImageField(allow_empty_file=True)
            }
        