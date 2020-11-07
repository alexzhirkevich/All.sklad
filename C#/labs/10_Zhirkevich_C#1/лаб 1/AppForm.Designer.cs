namespace лаб_1
{
    partial class AppWindow
    {
  
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.btnIncoding = new System.Windows.Forms.Button();
            this.OutLabel = new System.Windows.Forms.Label();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.openToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.openFileDialog = new System.Windows.Forms.OpenFileDialog();
            this.InpIncoding = new System.Windows.Forms.ListBox();
            this.OutIncoding = new System.Windows.Forms.ListBox();
            this.InpLabel = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnIncoding
            // 
            this.btnIncoding.Font = new System.Drawing.Font("Times New Roman", 15F);
            this.btnIncoding.Location = new System.Drawing.Point(191, 264);
            this.btnIncoding.Name = "btnIncoding";
            this.btnIncoding.Size = new System.Drawing.Size(200, 45);
            this.btnIncoding.TabIndex = 2;
            this.btnIncoding.Text = "Перекодировать";
            this.btnIncoding.UseVisualStyleBackColor = true;
            this.btnIncoding.Click += new System.EventHandler(this.btnIncoding_Click);
            // 
            // OutLabel
            // 
            this.OutLabel.AutoSize = true;
            this.OutLabel.Font = new System.Drawing.Font("Times New Roman", 15F);
            this.OutLabel.Location = new System.Drawing.Point(311, 72);
            this.OutLabel.Name = "OutLabel";
            this.OutLabel.Size = new System.Drawing.Size(235, 29);
            this.OutLabel.TabIndex = 4;
            this.OutLabel.Text = "Выходная кодировка";
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(582, 28);
            this.menuStrip1.TabIndex = 5;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openToolStripMenuItem});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(44, 24);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // openToolStripMenuItem
            // 
            this.openToolStripMenuItem.Name = "openToolStripMenuItem";
            this.openToolStripMenuItem.Size = new System.Drawing.Size(216, 26);
            this.openToolStripMenuItem.Text = "Choose File";
            this.openToolStripMenuItem.Click += new System.EventHandler(this.openToolStripMenuItem_Click);
            // 
            // InpIncoding
            // 
            this.InpIncoding.BackColor = System.Drawing.SystemColors.Menu;
            this.InpIncoding.Font = new System.Drawing.Font("Times New Roman", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.InpIncoding.FormattingEnabled = true;
            this.InpIncoding.ItemHeight = 29;
            this.InpIncoding.Items.AddRange(new object[] {
            "koi-8r",
            "windows-1251",
            "utf-8",
            "ibm-886"});
            this.InpIncoding.Location = new System.Drawing.Point(53, 120);
            this.InpIncoding.Name = "InpIncoding";
            this.InpIncoding.Size = new System.Drawing.Size(175, 120);
            this.InpIncoding.TabIndex = 7;
            this.InpIncoding.SelectedIndexChanged += new System.EventHandler(this.SelectInpIncoding);
            // 
            // OutIncoding
            // 
            this.OutIncoding.BackColor = System.Drawing.SystemColors.Menu;
            this.OutIncoding.Font = new System.Drawing.Font("Times New Roman", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.OutIncoding.FormattingEnabled = true;
            this.OutIncoding.ItemHeight = 29;
            this.OutIncoding.Items.AddRange(new object[] {
            "koi-8r",
            "windows-1251",
            "utf-8",
            "ibm-886"});
            this.OutIncoding.Location = new System.Drawing.Point(346, 120);
            this.OutIncoding.Name = "OutIncoding";
            this.OutIncoding.Size = new System.Drawing.Size(175, 120);
            this.OutIncoding.TabIndex = 8;
            this.OutIncoding.SelectedIndexChanged += new System.EventHandler(this.SelectOutIncoding);
            // 
            // InpLabel
            // 
            this.InpLabel.AutoSize = true;
            this.InpLabel.Font = new System.Drawing.Font("Times New Roman", 15F);
            this.InpLabel.Location = new System.Drawing.Point(29, 72);
            this.InpLabel.Name = "InpLabel";
            this.InpLabel.Size = new System.Drawing.Size(218, 29);
            this.InpLabel.TabIndex = 9;
            this.InpLabel.Text = "Входная кодировка";
            // 
            // AppForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.AppWorkspace;
            this.ClientSize = new System.Drawing.Size(582, 353);
            this.Controls.Add(this.InpLabel);
            this.Controls.Add(this.OutIncoding);
            this.Controls.Add(this.InpIncoding);
            this.Controls.Add(this.OutLabel);
            this.Controls.Add(this.btnIncoding);
            this.Controls.Add(this.menuStrip1);
            this.DoubleBuffered = true;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "AppForm";
            this.Text = "Перекодировка файла";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Button btnIncoding;
        private System.Windows.Forms.Label OutLabel;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem openToolStripMenuItem;
        private System.Windows.Forms.OpenFileDialog openFileDialog;
        private System.Windows.Forms.ListBox InpIncoding;
        private System.Text.Encoding OutputEncoding=null;
        private System.Text.Encoding InputEncoding=null;
        private System.Windows.Forms.ListBox OutIncoding;
        private System.Windows.Forms.Label InpLabel;
    }
}

