using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _1
{
    class AppWindow : Form
    {
        private void AppWindow_FormClosing(
            Object sender, FormClosingEventArgs e)
        {
            if (MessageBox.Show("Are you sure to exit?", Text, MessageBoxButtons.YesNo, MessageBoxIcon.Question) != DialogResult.Yes)
            {
                e.Cancel = true;
            }
        }

        private void AppWindow_Resize(
            object sender, System.EventArgs e)
        {
            ArrangeClientArea();
        }

        public AppWindow(string title,
            int width, int height)
        {
            FormClosing += AppWindow_FormClosing;
            Resize += AppWindow_Resize;

            Text = title;
            if (width > 0)
                Width = width;
            if (height > 0)
                Height = height;

            CenterToScreen();
            BuildControls();
        }
        private void BuildControls()
        {
            BuildToolTip();
            BuildMenu();
            BuildStatusLine();
            BuildButton();
            ArrangeClientArea();

            MinimumSize = AppMinSize(
                NcSize.Width =
                    (Width - ClientSize.Width + 1),
                NcSize.Height =
                    (Height - ClientSize.Height + 1));
        }
        private Size NcSize = new Size();

        private Rectangle GetClientRectangle()
        {
            return new Rectangle(
                NcSize.Width / 2,
                mnuMain.Bounds.Bottom,
                Width - NcSize.Width,
                statusStrip.Bounds.Top -
                   mnuMain.Bounds.Bottom);
        }
        private Size AppMinSize(int ncWidth, int ncHeight)
        {
            ncWidth += btnClickMe.Width;
            ncHeight += btnClickMe.Height;
            ncHeight += mnuMain.Height +
                statusStrip.Height;
            return new Size(ncWidth, ncHeight);
        }

        private void ArrangeClientArea()
        {
            // center button to client area:
            if (btnClickMe != null)
            {
                btnClickMe.SetBounds(
                   (ClientSize.Width -
                       btnClickMe.Width) / 2 + 1,
                   (ClientSize.Height -
                       btnClickMe.Height) / 2 + 1,
                   btnClickMe.Width,
                   btnClickMe.Height);
            }
        }
        // 1) declare GUI-control variables:
        //members for status line:
        private StatusStrip statusStrip;
        private
        ToolStripStatusLabel toolStripStatusLabel;

        private void BuildStatusLine()
        {   // 2) set-up GUI-controls:
            statusStrip = new StatusStrip();
            toolStripStatusLabel = new
                ToolStripStatusLabel();
            // freeze layout:
            statusStrip.SuspendLayout();
            SuspendLayout();

            statusStrip.Items.AddRange(
                new ToolStripItem[] {
                    toolStripStatusLabel });
            statusStrip.Location =
                new Point(0, 248);
            statusStrip.Name = "statusStrip";
            statusStrip.Size = new Size(292, 30);
            statusStrip.TabIndex = 0;
            statusStrip.Text = "statusStrip";

            toolStripStatusLabel.BorderSides = 0;
            toolStripStatusLabel.BorderStyle =
                Border3DStyle.Flat;
            toolStripStatusLabel.IsLink = false;
            toolStripStatusLabel.Name =
                "toolStripStatusLabel";
            toolStripStatusLabel.Size =
                new Size(246, 28);

            toolStripStatusLabel.Spring = true;
            // fill space
            toolStripStatusLabel.Text = "";
            toolStripStatusLabel.Alignment =
                   ToolStripItemAlignment.Left;
            toolStripStatusLabel.TextAlign =
                ContentAlignment.TopLeft;

            // 3) Insert GUI-control to 
            //       Form's ControlCollection
            Controls.Add(statusStrip);
            // defrost layout:
            statusStrip.ResumeLayout(false);
            statusStrip.PerformLayout();

            ResumeLayout(false);
            PerformLayout();
        }
        // 1) declare GUI-control variables:
        //members for simple menu:
        private MenuStrip mnuMain =
            new MenuStrip(); // all menu-system
        private ToolStripMenuItem mnuFile = new
            ToolStripMenuItem(); // pop-down menu
        private ToolStripMenuItem mnuFileChFolder =
             new ToolStripMenuItem();
        // pop-down menu item
        private ToolStripMenuItem mnuFileOpenFile =
             new ToolStripMenuItem();
        // pop-down menu item

        private ToolStripMenuItem
            mnuFileSaveAsFile =
             new ToolStripMenuItem();
        // pop-down menu item
        private ToolStripMenuItem mnuFileExit =
             new ToolStripMenuItem();
        // pop-down menu item
        private ToolStripMenuItem mnuHelp =
             new ToolStripMenuItem();
        // pop-down menu
        private ToolStripMenuItem mnuHelpAbout =
             new ToolStripMenuItem();
        // pop-down menu item

        private void BuildMenu()
        {
            // 2) set-up GUI-controls:
            //insert pop-down menu to main menu
            mnuFile.Text = "&File";
            //set text with Alt-command: Alt-F
            mnuMain.Items.Add(mnuFile);
            mnuHelp.Text = "&Help";
            //set text with Alt-command: Alt-H
            mnuMain.Items.Add(mnuHelp);
            //insert Choose Folder command to 
            //   pop-down menu
            mnuFileChFolder.Text =
                "&Choose Folder...";
            //set text with command: C
            mnuFile.DropDownItems.Add(
                mnuFileChFolder);

            // event for command
            //     (System.EventHandler)
            mnuFileChFolder.Click +=
                (o, s) => OnFileChooseFolder();
            mnuFileChFolder.MouseEnter +=
                (o, s) =>
                  toolStripStatusLabel.Text =
                     "Choose an existing folder";
            mnuFileChFolder.MouseLeave +=
                (o, s) =>
                  toolStripStatusLabel.Text = "";
            //insert Open command to pop-down menu
            mnuFileOpenFile.Text = "&Open";
            //set text with command: O
            mnuFile.DropDownItems.Add(
                mnuFileOpenFile);
            // set-up event handler for Open command
            mnuFileOpenFile.Click +=
                (o, s) => OnFileOpen();
            mnuFileOpenFile.MouseEnter += (o, s) =>
                  toolStripStatusLabel.Text =
                     "Open an existing file";
            mnuFileOpenFile.MouseLeave +=
                (o, s) =>
                  toolStripStatusLabel.Text = "";
            //insert SaveAs command to pop-down menu
            mnuFileSaveAsFile.Text = "&Save As";
            //set text with command: S
            mnuFile.DropDownItems.Add(
                mnuFileSaveAsFile);
            // event handler for command
            mnuFileSaveAsFile.Click +=
                (o, s) => OnFileSaveAs();
            mnuFileSaveAsFile.MouseEnter += (o, s) =>
                   toolStripStatusLabel.Text =
                     "Save a file with a new name";
            mnuFileSaveAsFile.MouseLeave +=
                 (o, s) =>
                   toolStripStatusLabel.Text = "";
            //insert Separator to pop-down menu
            mnuFile.DropDownItems.Add(
                new ToolStripSeparator());
            //insert Exit command to pop-down menu
            mnuFileExit.Text = "E&xit";
            //set text with command: x
            mnuFile.DropDownItems.Add(mnuFileExit);
            // handler for command
            mnuFileExit.Click +=
                (o, s) => Application.Exit();
            mnuFileExit.MouseEnter += (o, s) =>
                  toolStripStatusLabel.Text =
                     "Exit application...";
            mnuFileExit.MouseLeave += (o, s) =>
                  toolStripStatusLabel.Text = "";
            //insert About command to pop-down menu
            mnuHelpAbout.Text = "&About...";
            //set text with command: A
            mnuHelp.DropDownItems.Add(
                mnuHelpAbout);
            // set-up event handler
            mnuHelpAbout.Click += (o, s) =>
                OnHelpAbout();
            mnuHelpAbout.MouseEnter += (o, s) =>
                  toolStripStatusLabel.Text =
                     "Show information about " +
                     " the programm...";
            mnuHelpAbout.MouseLeave += (o, s) =>
                  toolStripStatusLabel.Text = "";
            // add menu to form:
            Controls.Add(mnuMain);
            MainMenuStrip = mnuMain;
            // special form member to seting-up
        }
        // 1) declare GUI-control variables:
        private Button btnClickMe = new Button();
        private void BuildButton()
        {
            // 2) set-up GUI-controls:
            btnClickMe.Text = "Click Me...";
            //set text
            btnClickMe.SetBounds(50, 50, 100, 50);
            //set bounds (x,y,width,height)
            btnClickMe.Click += (o, s) => {
                MessageBox.Show("You've done that!",
                                btnClickMe.Text);
            };
            btnClickMe.MouseEnter += (o, s) =>
                  toolStripStatusLabel.Text =
                    "Click button to action...";
            btnClickMe.MouseLeave += (o, s) =>
                  toolStripStatusLabel.Text = "";
            // Set up the ToolTip text
            toolTip.SetToolTip(
                btnClickMe as Control, "Click");
            // 3) Insert to ControlCollection
            Controls.Add(btnClickMe);
        }
        ToolTip toolTip = new ToolTip();

        private void BuildToolTip()
        {
            // Set up the delays for the ToolTip.
            toolTip.AutoPopDelay = 5000;
            toolTip.InitialDelay = 1000;
            toolTip.ReshowDelay = 500;
            // Force the ToolTip text to be 
            //   displayed whether or not 
            //   the form is active.
            toolTip.ShowAlways = true;
        }

        private void OnFileOpen()
        {
            using (OpenFileDialog openFileDialog =
                       new OpenFileDialog())
            {
                openFileDialog.InitialDirectory =
                    Environment.GetFolderPath(
                       Environment.SpecialFolder.
                           Personal);
                // My Documents folder.
                openFileDialog.Filter =
   "Text files (*.txt) |*.txt|All files (*.*) |*.*";
                openFileDialog.FilterIndex = 0;
                openFileDialog.DefaultExt = "txt";
                openFileDialog.FileName = "Text file";

                openFileDialog.RestoreDirectory =
                    true;
                openFileDialog.Multiselect =
                    false;
                // if true - use FileNames[]
                //    to get selected
                if (openFileDialog.ShowDialog() ==
                        DialogResult.OK)
                {
                    MessageBox.Show(
                      "File selected: " +
                        openFileDialog.FileName);
                }
            }
        }
        private void OnFileSaveAs()
        {
            using (SaveFileDialog saveFileDialog =
                       new SaveFileDialog())
            {
                saveFileDialog.InitialDirectory =
                    Environment.GetFolderPath(
                      Environment.SpecialFolder.
                        Personal);
                // My Documents folder.
                saveFileDialog.Filter =
"Text files (*.txt) |*.txt|All files (*.*) |*.*";
                saveFileDialog.FilterIndex = 0;
                saveFileDialog.DefaultExt = "txt";
                saveFileDialog.FileName = "*.txt";

                saveFileDialog.RestoreDirectory =
                    true;
                if (saveFileDialog.ShowDialog() ==
                        DialogResult.OK)
                {
                    MessageBox.Show(
                        "File selected: " +
                        saveFileDialog.FileName);
                }
            }
        }

        private void OnFileChooseFolder()
        {
            using (FolderBrowserDialog
                       folderBrowserDialog =
                           new FolderBrowserDialog())
            {
                // Set the help text
                folderBrowserDialog.Description =
      "Select the directory that you want to use";
                // Don't allow to create new files
                folderBrowserDialog.
                    ShowNewFolderButton = false;
                // My Documents folder:
                folderBrowserDialog.SelectedPath =
                    Environment.GetFolderPath(
                        Environment.SpecialFolder.Personal);
                folderBrowserDialog.RootFolder =
                        Environment.SpecialFolder.MyComputer;
                // Show the FolderBrowserDialog.
                if (folderBrowserDialog.
                        ShowDialog(this) ==
                            DialogResult.OK)
                {//SelectedPath=="" for MyComputer
                    MessageBox.Show("Folder choosen: " + folderBrowserDialog.SelectedPath);
                }
            }
        }

        private void OnHelpAbout()
        {
            MessageBox.Show(
                "Задача\n" +
"Постройте диаграмму опроса в сети\n" +
"отображающую процентное соотношение\n" +
"проголосовавших.\n\n" +
"Жиркевич Алесандр Юрьевич\n" +
"1 курс, 10 группа, ФПМИ, БГУ",
                "About...",
                MessageBoxButtons.OK,
                MessageBoxIcon.Information);
        }

        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Title title1 = new System.Windows.Forms.DataVisualization.Charting.Title();
            this.MainChart = new System.Windows.Forms.DataVisualization.Charting.Chart();
            ((System.ComponentModel.ISupportInitialize)(this.MainChart)).BeginInit();
            this.SuspendLayout();
            // 
            // MainChart
            // 
            this.MainChart.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            chartArea1.Name = "ChartArea1";
            this.MainChart.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.MainChart.Legends.Add(legend1);
            this.MainChart.Location = new System.Drawing.Point(136, 52);
            this.MainChart.Name = "MainChart";
            series1.ChartArea = "ChartArea1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Pie;
            series1.Legend = "Legend1";
            series1.Name = "Opros";
            series1.YValuesPerPoint = 2;
            this.MainChart.Series.Add(series1);
            this.MainChart.Size = new System.Drawing.Size(300, 300);
            this.MainChart.TabIndex = 0;
            this.MainChart.Text = "Chart";
            title1.Name = "Title1";
            title1.Text = "йцу";
            this.MainChart.Titles.Add(title1);
            // 
            // AppWindow
            // 
            this.ClientSize = new System.Drawing.Size(580, 402);
            this.Controls.Add(this.MainChart);
            this.Name = "AppWindow";
            ((System.ComponentModel.ISupportInitialize)(this.MainChart)).EndInit();
            this.ResumeLayout(false);

        }

        private System.Windows.Forms.DataVisualization.Charting.Chart MainChart;
    }
}
