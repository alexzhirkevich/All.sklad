namespace лаб_2
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
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.chooseFileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.btnDeShifr = new System.Windows.Forms.Button();
            this.btnLoadKey = new System.Windows.Forms.Button();
            this.btnGetKey = new System.Windows.Forms.Button();
            this.btnShifr = new System.Windows.Forms.Button();
            this.Шифровка = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.menuStrip1.SuspendLayout();
            this.Шифровка.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(282, 28);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.chooseFileToolStripMenuItem});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(44, 24);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // chooseFileToolStripMenuItem
            // 
            this.chooseFileToolStripMenuItem.Name = "chooseFileToolStripMenuItem";
            this.chooseFileToolStripMenuItem.Size = new System.Drawing.Size(158, 26);
            this.chooseFileToolStripMenuItem.Text = "Choose file";
            this.chooseFileToolStripMenuItem.Click += new System.EventHandler(this.ChoooseFileStrip_click);
            // 
            // button3
            // 
            this.btnDeShifr.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnDeShifr.Location = new System.Drawing.Point(34, 111);
            this.btnDeShifr.Name = "button3";
            this.btnDeShifr.Size = new System.Drawing.Size(120, 60);
            this.btnDeShifr.TabIndex = 1;
            this.btnDeShifr.Text = "Расшифровать";
            this.btnDeShifr.Enabled = false;
            this.btnDeShifr.UseVisualStyleBackColor = true;
            this.btnDeShifr.Click += new System.EventHandler(this.btnDeShifr_click);
            // 
            // button4
            // 
            this.btnLoadKey.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnLoadKey.Location = new System.Drawing.Point(34, 18);
            this.btnLoadKey.Name = "button4";
            this.btnLoadKey.Size = new System.Drawing.Size(120, 60);
            this.btnLoadKey.TabIndex = 3;
            this.btnLoadKey.Text = "Ввести ключ";
            this.btnLoadKey.UseVisualStyleBackColor = true;
            this.btnLoadKey.Click += new System.EventHandler(this.btnGetKey_click);
            // 
            // button2
            // 
            this.btnGetKey.Location = new System.Drawing.Point(34, 111);
            this.btnGetKey.Name = "button2";
            this.btnGetKey.Size = new System.Drawing.Size(120, 60);
            this.btnGetKey.TabIndex = 3;
            this.btnGetKey.Text = "Получить ключ";
            this.btnGetKey.UseVisualStyleBackColor = true;
            this.btnGetKey.Enabled = false;
            this.btnGetKey.Click += new System.EventHandler(this.btnWriteKey_click);
            // 
            // button1
            // 
            this.btnShifr.Location = new System.Drawing.Point(34, 18);
            this.btnShifr.Name = "button1";
            this.btnShifr.Size = new System.Drawing.Size(120, 60);
            this.btnShifr.TabIndex = 1;
            this.btnShifr.Text = "Зашифровать";
            this.btnShifr.UseVisualStyleBackColor = true;
            this.btnShifr.Click += new System.EventHandler(this.btnShifr_click);
            // 
            // Шифровка
            // 
            this.Шифровка.Controls.Add(this.tabPage1);
            this.Шифровка.Controls.Add(this.tabPage2);
            this.Шифровка.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Шифровка.Location = new System.Drawing.Point(31, 33);
            this.Шифровка.Name = "Шифровка";
            this.Шифровка.SelectedIndex = 0;
            this.Шифровка.Size = new System.Drawing.Size(200, 227);
            this.Шифровка.TabIndex = 6;
            this.Шифровка.Tag = "Шифровка";
            // 
            // tabPage1
            // 
            this.tabPage1.BackColor = System.Drawing.Color.PaleGreen;
            this.tabPage1.Controls.Add(this.btnShifr);
            this.tabPage1.Controls.Add(this.btnGetKey);
            this.tabPage1.Location = new System.Drawing.Point(4, 26);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(192, 197);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Шифровка";
            // 
            // tabPage2
            // 
            this.tabPage2.BackColor = System.Drawing.Color.PaleGreen;
            this.tabPage2.Controls.Add(this.btnDeShifr);
            this.tabPage2.Controls.Add(this.btnLoadKey);
            this.tabPage2.Location = new System.Drawing.Point(4, 26);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(192, 197);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Расшифровка";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.Шифровка);
            this.groupBox1.Location = new System.Drawing.Point(13, 31);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(260, 280);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Info;
            this.ClientSize = new System.Drawing.Size(282, 323);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Шифр";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.Шифровка.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem chooseFileToolStripMenuItem;
        private System.Windows.Forms.Button btnDeShifr;
        private System.Windows.Forms.Button btnLoadKey;
        private System.Windows.Forms.Button btnGetKey;
        private System.Windows.Forms.Button btnShifr;
        private System.Windows.Forms.TabControl Шифровка;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.GroupBox groupBox1;
    }
}

