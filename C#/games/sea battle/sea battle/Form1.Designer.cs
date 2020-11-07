namespace sea_battle
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
            this.panel = new System.Windows.Forms.Panel();
            this.field1 = new System.Windows.Forms.Panel();
            this.field2 = new System.Windows.Forms.Panel();
            this.SuspendLayout();
            // 
            // panel
            // 
            this.panel.BackColor = System.Drawing.Color.MediumAquamarine;
            this.panel.Location = new System.Drawing.Point(0, 0);
            this.panel.Name = "panel";
            this.panel.Size = new System.Drawing.Size(1280, 70);
            this.panel.TabIndex = 0;
            this.panel.MouseDown += new System.Windows.Forms.MouseEventHandler(this.panel1_MouseDown);
            this.panel.MouseMove += new System.Windows.Forms.MouseEventHandler(this.panel1_MouseMove);
            // 
            // field1
            // 
            this.field1.BackColor = System.Drawing.Color.LightCyan;
            this.field1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.field1.Cursor = System.Windows.Forms.Cursors.Default;
            this.field1.Location = new System.Drawing.Point(50, 150);
            this.field1.Name = "field1";
            this.field1.Size = new System.Drawing.Size(500, 500);
            this.field1.TabIndex = 1;
            // 
            // field2
            // 
            this.field2.BackColor = System.Drawing.Color.LightCyan;
            this.field2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.field2.Cursor = System.Windows.Forms.Cursors.Default;
            this.field2.Location = new System.Drawing.Point(730, 150);
            this.field2.Name = "field2";
            this.field2.Size = new System.Drawing.Size(500, 500);
            this.field2.TabIndex = 2;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1280, 720);
            this.Controls.Add(this.field2);
            this.Controls.Add(this.field1);
            this.Controls.Add(this.panel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form1";
            this.Text = "Form1";
            this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.panel1_MouseDown);
            this.MouseMove += new System.Windows.Forms.MouseEventHandler(this.panel1_MouseMove);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel;
        private System.Windows.Forms.Panel field1;
        private System.Windows.Forms.Panel field2;
    }
}

