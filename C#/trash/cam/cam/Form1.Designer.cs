using AForge.Controls;
using System;
using System.Runtime.CompilerServices;
using System.Windows.Forms;
using AForge.Video;
using AForge.Video.DirectShow;
using System.Media;
using AForge.Controls;

namespace cam
{
    partial class Form1
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        /// 
        private VideoSourcePlayer videoSourcePlayer = new VideoSourcePlayer();       
        private VideoCaptureDevice videoCaptureDevice;
       
        private void InitializeComponent()
        { 
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.onClose);
            this.ClientSizeChanged += new System.EventHandler(this.updateBounds)
            this.ResumeLayout(false);

        }

        private void updateBounds(object sender, EventArgs e)
        {
            this.videoSourcePlayer.SetBounds(this.ClientRectangle.X,this.ClientRectangle.Y,this.ClientRectangle.Width, this.ClientRectangle.Height);
        }

        private void onClose(object sender, FormClosedEventArgs e)
        {
            if (videoSourcePlayer.VideoSource != null) {
                videoSourcePlayer.VideoSource.Stop();
            }
        }

        #endregion

        private System.IO.FileSystemWatcher fileSystemWatcher1;
    }
}

