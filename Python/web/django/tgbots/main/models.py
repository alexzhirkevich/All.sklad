from django.db import models

# Create your models here.

class QRCode(models.Model):
    text = models.CharField('Текст', max_length = 50)
    img = models.ImageField(verbose_name="QR код",blank = True, null=True, upload_to ="QRcodes/")

    def __str__(self):
        return self.username