using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace lab_4_cs
{
    public partial class AppWindow : Form
    {
        private double a;
        private double b;
        private double c;
        private OpenFileDialog openFileDialog = new OpenFileDialog();
        private FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
        static public int widthCnt = 0;
        static public int heightCnt = 0;
        private Pen redPen = new Pen(Color.Red, 2);
        private Image img;
        private bool process = false;
        private System.Threading.Thread th;
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public AppWindow()
        {
            try
            {
                InitializeComponent();
                Resize += AppWindow_Resize;
                FormClosing += AppWindow_Close;
                a = (double)pictureBox.Width / (double)pictureBox.Height;
                b = (double)ClientSize.Height / (double)pictureBox.Height;
                c = (double)ClientSize.Width / (double)pictureBox.Width;
                SetBounds();
                this.CenterToScreen();
            }
            catch
            {
                MessageBox.Show("Ошибка инициализации приложения. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void SetBounds()
        {
            try
            {
                btnCut.SetBounds(ClientSize.Width / 4 - btnCut.Width / 2, ClientSize.Height - btnCut.Height - 10,
                    btnCut.Width, btnCut.Height);
                btnSave.SetBounds(ClientSize.Width * 3 / 4 - btnSave.Width / 2, ClientSize.Height - btnSave.Height - 10,
                    btnSave.Width, btnSave.Height);
                if (pictureBox.Width + 30 > ClientSize.Width)
                    pictureBox.SetBounds((ClientSize.Width - pictureBox.Width) / 2, menuStrip1.Height + 1,
                        (int)(ClientSize.Width / c), (int)(pictureBox.Width / a));
                else
                    pictureBox.SetBounds((ClientSize.Width - pictureBox.Width) / 2, menuStrip1.Height + 1,
                        (int)(pictureBox.Height * a), (int)(ClientSize.Height / b));
            }
            catch
            {
                MessageBox.Show("Ошибка изменения размера окна. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void AppWindow_Close(object sender, FormClosingEventArgs e)
        { 
            if (process)
            {
                DialogResult res = MessageBox.Show("Происходит разрезание картинок. \nПродолжить в фоновом режиме?", "Confirm", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question);
                if (res == DialogResult.Cancel)
                {
                    e.Cancel = true;
                }
                else
                    if (res == DialogResult.No)
                {
                    th.Abort();
                }
                else
                {
                    this.Hide();
                    th.Join();
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void AppWindow_Resize(object sender, System.EventArgs e)
        {
            try
            {
                if (this.WindowState == System.Windows.Forms.FormWindowState.Minimized)
                    return;
                if (this.WindowState == System.Windows.Forms.FormWindowState.Normal)
                {
                    SetBounds();
                }
                else
                {
                    btnCut.SetBounds(ClientSize.Width / 4 - btnCut.Width / 2, ClientSize.Height - btnCut.Height - 10,
                        btnCut.Width, btnCut.Height);
                    btnSave.SetBounds(ClientSize.Width * 3 / 4 - btnSave.Width / 2, ClientSize.Height - btnSave.Height - 10,
                        btnSave.Width, btnSave.Height);
                    pictureBox.Height = (int)(ClientSize.Height / b);
                    pictureBox.Width = (int)(pictureBox.Height * a);
                    pictureBox.SetBounds((ClientSize.Width - pictureBox.Width) / 2, menuStrip1.Height + 1,
                        pictureBox.Width, pictureBox.Height);
                }
                SetBounds();
            }
            catch
            {
                MessageBox.Show("Ошибка изменения размера окна. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void ExitMenuStrip(object sender, EventArgs e)
        {
            Application.Exit();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        static public void TextBoxKeyPressEvent(object sender, KeyPressEventArgs e)
        {
            try
            {
                char number = e.KeyChar;

                if (!Char.IsDigit(number) && number != 8)
                {
                    e.Handled = true;
                }
            }
            catch
            {
                MessageBox.Show("Ошибка текстового поля. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void ChooseFileMenuStrip(object sender, EventArgs e)
        {
            try
            {
                widthCnt = 0;
                heightCnt = 0;

                this.btnSave.Enabled = false;

                if (openFileDialog.ShowDialog() != DialogResult.OK)
                    return;

                img = Image.FromFile(openFileDialog.FileName);

                this.pictureBox.Image = img;
                this.btnCut.Enabled = true;
                this.pictureBox.Width = (int)(pictureBox.Height * ((double)img.Width / (double)img.Height));
                this.pictureBox.SetBounds((ClientSize.Width - pictureBox.Width) / 2, this.pictureBox.Location.Y,
                    this.pictureBox.Width, this.pictureBox.Height);
                a = (double)img.Width / (double)img.Height;
                b = (double)ClientSize.Height / (double)pictureBox.Height;
                c = (double)ClientSize.Width / (double)pictureBox.Width;
            }
            catch
            {
                MessageBox.Show("Не удалось открыть файл. Возможно, формат не поддерживается", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void pictureBoxPaint(object sender, PaintEventArgs e)
        {
            try
            {
                if (widthCnt == 0 || heightCnt == 0)
                    return;
                for (int i = 1; i < widthCnt; i++)
                {
                    PointF one = new PointF((float)this.pictureBox.DisplayRectangle.Left,
                        (float)this.pictureBox.DisplayRectangle.Bottom / (float)widthCnt * (float)i);
                    PointF two = new PointF((float)this.pictureBox.DisplayRectangle.Right,
                       (float)this.pictureBox.DisplayRectangle.Bottom / (float)widthCnt * (float)i);

                    e.Graphics.DrawLine(redPen, one, two);
                }
                for (int i = 1; i < heightCnt; i++)
                {
                    PointF one = new PointF((float)this.pictureBox.DisplayRectangle.Right / (float)heightCnt * (float)i,
                        (float)this.pictureBox.DisplayRectangle.Top);
                    PointF two = new PointF(this.pictureBox.DisplayRectangle.Right / (float)heightCnt * (float)i,
                       (float)this.pictureBox.DisplayRectangle.Bottom);

                    e.Graphics.DrawLine(redPen, one, two);
                }
            }
            catch
            {
                MessageBox.Show("Ошибка отрисовки изображения. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private void btnCut_Click(object sender, EventArgs e)
        {
            try
            {
                Dlg dlg = new Dlg();
                dlg.Owner = this;
                dlg.ShowDialog();
                if (widthCnt > 1 || heightCnt > 1)
                {
                    pictureBox.Invalidate();
                    btnSave.Enabled = true;
                }
            }
            catch
            {
                MessageBox.Show("Не удалось разделить изображение. Перезапустите приложение", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
        private void SaveImg()
        {
            process = true;
            btnCut.Enabled = false;
            btnSave.Enabled = false;
            this.menuStrip1.Enabled = false;
            SizeF size = new SizeF((float)img.Width / (float)widthCnt, (float)img.Height / (float)heightCnt);
            String date = DateTime.Now.ToString();
            date = date.Replace('.', '-');
            date = date.Replace(':', '-');
            date = date.Replace(' ', '-');
            using (Bitmap src = new Bitmap(img, img.Width, img.Height), CuttedImage = new Bitmap((int)size.Width, (int)size.Height))
            {
                int imgCnt = 1;
                for (int i = 0; i < widthCnt; i++)
                    for (int j = 0; j < heightCnt; j++)
                    {
                        PointF point = new PointF((float)img.Width / (float)widthCnt * (float)i,
                            (float)img.Height / (float)heightCnt * (float)j);

                        RectangleF cutrect = new RectangleF(point, size);

                        Graphics g = Graphics.FromImage(CuttedImage);

                        g.DrawImage(src, 0, 0, cutrect, GraphicsUnit.Pixel);

                        CuttedImage.Save(folderBrowserDialog.SelectedPath + "\\" + date + "-img-" + (imgCnt++).ToString() + ".gif",
                            System.Drawing.Imaging.ImageFormat.Gif);
                    }
                btnCut.Enabled = true;
                btnSave.Enabled = true;
                this.menuStrip1.Enabled = true;
                process = false;
                MessageBox.Show("Изображения успешно сохранены", "Разрезатель картинок",
                        MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void BtnSave_Click(object sender, EventArgs e)
        {
            try
            {
               
 
                if (folderBrowserDialog.ShowDialog() != DialogResult.OK)
                    return;
                th = new System.Threading.Thread(SaveImg);
                th.Start();    
            }
            catch
            {
                MessageBox.Show("Не удалось сохранить изображения", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
