namespace krestiki_noliki
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.Panel = new System.Windows.Forms.Panel();
            this.ScoreBoard = new System.Windows.Forms.Label();
            this.ResultLable = new System.Windows.Forms.Label();
            this.Restart = new System.Windows.Forms.PictureBox();
            this.btnMinimize = new System.Windows.Forms.Button();
            this.btnClose = new System.Windows.Forms.Button();
            this.panel2 = new System.Windows.Forms.Panel();
            this.BottomRight = new System.Windows.Forms.Panel();
            this.Bottom = new System.Windows.Forms.Panel();
            this.BottomLeft = new System.Windows.Forms.Panel();
            this.Right = new System.Windows.Forms.Panel();
            this.Left = new System.Windows.Forms.Panel();
            this.TopRight = new System.Windows.Forms.Panel();
            this.Center = new System.Windows.Forms.Panel();
            this.Top = new System.Windows.Forms.Panel();
            this.TopLeft = new System.Windows.Forms.Panel();
            this.Panel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Restart)).BeginInit();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // Panel
            // 
            this.Panel.BackColor = System.Drawing.Color.Teal;
            this.Panel.Controls.Add(this.ScoreBoard);
            this.Panel.Controls.Add(this.ResultLable);
            this.Panel.Controls.Add(this.Restart);
            this.Panel.Controls.Add(this.btnMinimize);
            this.Panel.Controls.Add(this.btnClose);
            this.Panel.Dock = System.Windows.Forms.DockStyle.Top;
            this.Panel.Location = new System.Drawing.Point(0, 0);
            this.Panel.Name = "Panel";
            this.Panel.Size = new System.Drawing.Size(500, 100);
            this.Panel.TabIndex = 0;
            this.Panel.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Form_MouseDown);
            this.Panel.MouseMove += new System.Windows.Forms.MouseEventHandler(this.Form_MouseMove);
            // 
            // ScoreBoard
            // 
            this.ScoreBoard.BackColor = System.Drawing.Color.Transparent;
            this.ScoreBoard.Font = new System.Drawing.Font("Bahnschrift Condensed", 19.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.ScoreBoard.ForeColor = System.Drawing.Color.Black;
            this.ScoreBoard.Location = new System.Drawing.Point(112, -11);
            this.ScoreBoard.Name = "ScoreBoard";
            this.ScoreBoard.Size = new System.Drawing.Size(286, 58);
            this.ScoreBoard.TabIndex = 4;
            this.ScoreBoard.Text = "You 0 : 0 Comp";
            this.ScoreBoard.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.ScoreBoard.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Form_MouseDown);
            this.ScoreBoard.MouseMove += new System.Windows.Forms.MouseEventHandler(this.Form_MouseMove);
            // 
            // ResultLable
            // 
            this.ResultLable.BackColor = System.Drawing.Color.Transparent;
            this.ResultLable.Font = new System.Drawing.Font("Bahnschrift Condensed", 28.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.ResultLable.ForeColor = System.Drawing.Color.Red;
            this.ResultLable.Location = new System.Drawing.Point(161, 42);
            this.ResultLable.Name = "ResultLable";
            this.ResultLable.Size = new System.Drawing.Size(189, 58);
            this.ResultLable.TabIndex = 3;
            this.ResultLable.Text = "Result lable";
            this.ResultLable.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.ResultLable.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Form_MouseDown);
            this.ResultLable.MouseMove += new System.Windows.Forms.MouseEventHandler(this.Form_MouseMove);
            // 
            // Restart
            // 
            this.Restart.BackColor = System.Drawing.Color.Transparent;
            this.Restart.BackgroundImage = global::krestiki_noliki.Properties.Resources.restart;
            this.Restart.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Restart.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Restart.Location = new System.Drawing.Point(25, 25);
            this.Restart.Name = "Restart";
            this.Restart.Size = new System.Drawing.Size(51, 50);
            this.Restart.TabIndex = 2;
            this.Restart.TabStop = false;
            this.Restart.Click += new System.EventHandler(this.Restart_Click);
            this.Restart.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Restart_MouseDown);
            this.Restart.MouseEnter += new System.EventHandler(this.Restart_MouseEnter);
            this.Restart.MouseLeave += new System.EventHandler(this.Restart_MouseLeave);
            this.Restart.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Restart_MouseUp);
            // 
            // btnMinimize
            // 
            this.btnMinimize.BackColor = System.Drawing.Color.Transparent;
            this.btnMinimize.BackgroundImage = global::krestiki_noliki.Properties.Resources.minimize;
            this.btnMinimize.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnMinimize.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnMinimize.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMinimize.Location = new System.Drawing.Point(431, 3);
            this.btnMinimize.Name = "btnMinimize";
            this.btnMinimize.Size = new System.Drawing.Size(30, 30);
            this.btnMinimize.TabIndex = 1;
            this.btnMinimize.UseVisualStyleBackColor = false;
            this.btnMinimize.Click += new System.EventHandler(this.button2_Click);
            this.btnMinimize.MouseDown += new System.Windows.Forms.MouseEventHandler(this.button2_MouseDown);
            this.btnMinimize.MouseUp += new System.Windows.Forms.MouseEventHandler(this.button2_MouseUp);
            // 
            // btnClose
            // 
            this.btnClose.BackColor = System.Drawing.Color.Transparent;
            this.btnClose.BackgroundImage = global::krestiki_noliki.Properties.Resources.x_mark;
            this.btnClose.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnClose.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnClose.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnClose.Location = new System.Drawing.Point(467, 3);
            this.btnClose.Name = "btnClose";
            this.btnClose.Size = new System.Drawing.Size(30, 30);
            this.btnClose.TabIndex = 0;
            this.btnClose.UseVisualStyleBackColor = false;
            this.btnClose.Click += new System.EventHandler(this.button1_Click);
            this.btnClose.MouseDown += new System.Windows.Forms.MouseEventHandler(this.button1_MouseDown);
            this.btnClose.MouseUp += new System.Windows.Forms.MouseEventHandler(this.button1_MouseUp);
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.AliceBlue;
            this.panel2.Controls.Add(this.BottomRight);
            this.panel2.Controls.Add(this.Bottom);
            this.panel2.Controls.Add(this.BottomLeft);
            this.panel2.Controls.Add(this.Right);
            this.panel2.Controls.Add(this.Left);
            this.panel2.Controls.Add(this.TopRight);
            this.panel2.Controls.Add(this.Center);
            this.panel2.Controls.Add(this.Top);
            this.panel2.Controls.Add(this.TopLeft);
            this.panel2.Location = new System.Drawing.Point(25, 125);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(450, 420);
            this.panel2.TabIndex = 1;
            // 
            // BottomRight
            // 
            this.BottomRight.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.BottomRight.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.BottomRight.Cursor = System.Windows.Forms.Cursors.Hand;
            this.BottomRight.Location = new System.Drawing.Point(300, 280);
            this.BottomRight.Name = "BottomRight";
            this.BottomRight.Size = new System.Drawing.Size(150, 140);
            this.BottomRight.TabIndex = 8;
            this.BottomRight.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.BottomRight.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.BottomRight.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.BottomRight.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.BottomRight.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Bottom
            // 
            this.Bottom.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Bottom.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Bottom.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Bottom.Location = new System.Drawing.Point(150, 280);
            this.Bottom.Name = "Bottom";
            this.Bottom.Size = new System.Drawing.Size(150, 140);
            this.Bottom.TabIndex = 7;
            this.Bottom.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.Bottom.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.Bottom.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.Bottom.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.Bottom.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // BottomLeft
            // 
            this.BottomLeft.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.BottomLeft.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.BottomLeft.Cursor = System.Windows.Forms.Cursors.Hand;
            this.BottomLeft.Location = new System.Drawing.Point(0, 280);
            this.BottomLeft.Name = "BottomLeft";
            this.BottomLeft.Size = new System.Drawing.Size(150, 140);
            this.BottomLeft.TabIndex = 6;
            this.BottomLeft.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.BottomLeft.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.BottomLeft.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.BottomLeft.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.BottomLeft.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Right
            // 
            this.Right.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Right.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Right.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Right.Location = new System.Drawing.Point(300, 140);
            this.Right.Name = "Right";
            this.Right.Size = new System.Drawing.Size(150, 140);
            this.Right.TabIndex = 5;
            this.Right.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.Right.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.Right.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.Right.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.Right.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Left
            // 
            this.Left.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Left.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Left.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Left.Location = new System.Drawing.Point(0, 140);
            this.Left.Name = "Left";
            this.Left.Size = new System.Drawing.Size(150, 140);
            this.Left.TabIndex = 4;
            this.Left.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.Left.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.Left.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.Left.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.Left.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // TopRight
            // 
            this.TopRight.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.TopRight.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.TopRight.Cursor = System.Windows.Forms.Cursors.Hand;
            this.TopRight.Location = new System.Drawing.Point(300, 0);
            this.TopRight.Name = "TopRight";
            this.TopRight.Size = new System.Drawing.Size(150, 140);
            this.TopRight.TabIndex = 3;
            this.TopRight.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.TopRight.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.TopRight.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.TopRight.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.TopRight.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Center
            // 
            this.Center.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Center.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Center.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Center.Location = new System.Drawing.Point(150, 140);
            this.Center.Name = "Center";
            this.Center.Size = new System.Drawing.Size(150, 140);
            this.Center.TabIndex = 2;
            this.Center.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.Center.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.Center.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.Center.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.Center.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Top
            // 
            this.Top.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.Top.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Top.Cursor = System.Windows.Forms.Cursors.Hand;
            this.Top.Location = new System.Drawing.Point(150, 0);
            this.Top.Name = "Top";
            this.Top.Size = new System.Drawing.Size(150, 140);
            this.Top.TabIndex = 1;
            this.Top.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.Top.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.Top.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.Top.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.Top.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // TopLeft
            // 
            this.TopLeft.BackColor = System.Drawing.Color.Azure;
            this.TopLeft.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.TopLeft.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.TopLeft.Cursor = System.Windows.Forms.Cursors.Hand;
            this.TopLeft.Location = new System.Drawing.Point(0, 0);
            this.TopLeft.Name = "TopLeft";
            this.TopLeft.Size = new System.Drawing.Size(150, 140);
            this.TopLeft.TabIndex = 0;
            this.TopLeft.MouseClick += new System.Windows.Forms.MouseEventHandler(this.onClick);
            this.TopLeft.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseDown);
            this.TopLeft.MouseEnter += new System.EventHandler(this.Cell_MouseEnter);
            this.TopLeft.MouseLeave += new System.EventHandler(this.Cell_MouseLeave);
            this.TopLeft.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Cell_MouseUp);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.ClientSize = new System.Drawing.Size(500, 569);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.Panel);
            this.Cursor = System.Windows.Forms.Cursors.Default;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Icon = Properties.Resources.icon;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Form1";
            this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Form_MouseDown);
            this.MouseMove += new System.Windows.Forms.MouseEventHandler(this.Form_MouseMove);
            this.Panel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.Restart)).EndInit();
            this.panel2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel Panel;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Panel TopLeft;
        private System.Windows.Forms.Panel BottomRight;
        private System.Windows.Forms.Panel Bottom;
        private System.Windows.Forms.Panel BottomLeft;
        private System.Windows.Forms.Panel Right;
        private System.Windows.Forms.Panel Left;
        private System.Windows.Forms.Panel TopRight;
        private System.Windows.Forms.Panel Center;
        private System.Windows.Forms.Panel Top;
        private System.Windows.Forms.Button btnMinimize;
        private System.Windows.Forms.Button btnClose;
        private System.Windows.Forms.PictureBox Restart;
        private System.Windows.Forms.Label ResultLable;
        private System.Windows.Forms.Label ScoreBoard;
    }
}

