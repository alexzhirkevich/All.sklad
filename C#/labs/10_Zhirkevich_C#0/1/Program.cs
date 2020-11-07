using System;
using System.Drawing;
using System.Windows.Forms;
using System.IO;
using System.Collections.Generic;
namespace Task0
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();

            Application.Run(new AppWindow("Задача", 800, 600));
        }
    }
    class AppWindow : Form
    {
        private void AppWindow_FormClosing(
            Object sender, FormClosingEventArgs e)
        {
            if (MessageBox.Show("Вы уверены, что хотите выйти?", Text, MessageBoxButtons.YesNo, MessageBoxIcon.Question) != DialogResult.Yes)
            {
                e.Cancel = true;
            }
        }

        private void AppWindow_Resize(
            object sender, System.EventArgs e)
        {
            ArrangeClientArea();
        }

        public AppWindow(string title, int width, int height)
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

            MinimumSize = AppMinSize(NcSize.Width =(Width - ClientSize.Width + 1), NcSize.Height =(Height - ClientSize.Height + 1));
        }
        private Size NcSize = new Size();

        private Rectangle GetClientRectangle()
        {
            return new Rectangle(
                NcSize.Width / 2,
                mnuMain.Bounds.Bottom,
                Width - NcSize.Width,
                statusStrip.Bounds.Top - mnuMain.Bounds.Bottom);
        }
        private Size AppMinSize(int ncWidth,  int ncHeight) 
        {
            ncWidth += btnChart.Width;
            ncHeight += btnChart.Height;
            ncHeight += mnuMain.Height + statusStrip.Height;
            return new Size(ncWidth, ncHeight);
        }

        private void ArrangeClientArea()
        {
            if (btnChart != null)
            {
                btnChart.SetBounds(
                   (ClientSize.Width - btnChart.Width) / 2 + 1,
                   (ClientSize.Height-btnChart.Height-40) + 1,
                   btnChart.Width,
                   btnChart.Height);
            }
            if (ChartName != "")
                MainChart.SetBounds(0, 20, ClientSize.Width, ClientSize.Height *8/10);
        }
       
        private StatusStrip statusStrip;
        private ToolStripStatusLabel toolStripStatusLabel;

        private void BuildStatusLine()
        { 
            statusStrip = new StatusStrip();
            toolStripStatusLabel = new ToolStripStatusLabel();
            statusStrip.SuspendLayout();
            SuspendLayout();

            statusStrip.Items.AddRange( new ToolStripItem[] { toolStripStatusLabel });
            statusStrip.Location = new Point(0, 248);
            statusStrip.Name = "statusStrip";
            statusStrip.Size = new Size(292, 30);
            statusStrip.TabIndex = 0;
            statusStrip.Text = "statusStrip";

            toolStripStatusLabel.BorderSides = 0;
            toolStripStatusLabel.BorderStyle =  Border3DStyle.Flat;
            toolStripStatusLabel.IsLink = false;
            toolStripStatusLabel.Name ="toolStripStatusLabel";
            toolStripStatusLabel.Size = new Size(246, 28);

            toolStripStatusLabel.Spring = true;
            toolStripStatusLabel.Text = "";
            toolStripStatusLabel.Alignment = ToolStripItemAlignment.Left;
            toolStripStatusLabel.TextAlign = ContentAlignment.TopLeft;

            Controls.Add(statusStrip);
            statusStrip.ResumeLayout(false);
            statusStrip.PerformLayout();

            ResumeLayout(false);
            PerformLayout();
        }
  
        private MenuStrip mnuMain = new MenuStrip(); 
        private ToolStripMenuItem mnuFile = new ToolStripMenuItem(); 
        private ToolStripMenuItem mnuFileOpenFile = new ToolStripMenuItem();
        private ToolStripMenuItem mnuFileExit = new ToolStripMenuItem();
        private ToolStripMenuItem mnuHelp = new ToolStripMenuItem();
        private ToolStripMenuItem mnuHelpAbout = new ToolStripMenuItem();
  
        private void BuildMenu()
        {
            mnuFile.Text = "&File";
            mnuMain.Items.Add(mnuFile);
            mnuHelp.Text = "&Help";
            mnuMain.Items.Add(mnuHelp);

            mnuFileOpenFile.Text = "&Open";
            mnuFile.DropDownItems.Add(mnuFileOpenFile);
            mnuFileOpenFile.Click += (o, s) => OnFileOpen();
            mnuFileOpenFile.MouseEnter += (o, s) => toolStripStatusLabel.Text = "Open an existing file";
            mnuFileOpenFile.MouseLeave += (o, s) => toolStripStatusLabel.Text = "";
           
            mnuFileExit.Text = "Quit";
            mnuFile.DropDownItems.Add(mnuFileExit);
            mnuFileExit.Click += (o, s) => Application.Exit();
            mnuFileExit.MouseEnter += (o, s) => toolStripStatusLabel.Text = "Exit application...";
            mnuFileExit.MouseLeave += (o, s) => toolStripStatusLabel.Text = "";
            
            mnuHelpAbout.Text = "&About...";
            mnuHelp.DropDownItems.Add(mnuHelpAbout);
            mnuHelpAbout.Click += (o, s) => OnHelpAbout();
            mnuHelpAbout.MouseEnter += (o, s) => toolStripStatusLabel.Text = "Show information about the programm...";
            mnuHelpAbout.MouseLeave += (o, s) => toolStripStatusLabel.Text = "";


            Controls.Add(mnuMain);
            MainMenuStrip = mnuMain;
        }

        void OnChangeFont()
        {

        }

        private Button btnChart = new Button();
        private void BuildButton()
        {
            btnChart.Text = "Построить диаграмму";
            btnChart.Font = new Font(btnChart.Font.Name, 10, btnChart.Font.Style, btnChart.Font.Unit);
            btnChart.SetBounds(50, 50, 150, 50);
            btnChart.Click += (o,s) =>onButtonClick();
            btnChart.MouseEnter += (o, s) => toolStripStatusLabel.Text = "Нажмите на кнопку, чтобы построить диаграмму";
            btnChart.MouseLeave += (o, s) => toolStripStatusLabel.Text = "";
            toolTip.SetToolTip( btnChart as Control, "Click");
            
            Controls.Add(btnChart);
        }

        private Boolean once = true;
        private String oldFileName = "";
        private void onButtonClick()
        {
            if (ChartName == "" && !OnFileOpen())
            {
                while (MessageBox.Show("Файл с данными не выбран", "Ошибка", MessageBoxButtons.RetryCancel, MessageBoxIcon.Error) == DialogResult.Retry)
                    if (OnFileOpen())
                        break;
                    return;
            }
            if (once)
            {
                BuildChart(ChartName);
                once = false;
            }
            while (FileName == oldFileName)
            {
                if (MessageBox.Show("Вы собираетесь построить ту же диаграмму.\n" +
                    "Желаете загрузить новые данные?", "Задача", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
                    return;

                if (!OnFileOpen())
                {
                    while (true)
                    {
                        if (MessageBox.Show("Файл с данными не выбран", "Ошибка", MessageBoxButtons.RetryCancel, MessageBoxIcon.Error) == DialogResult.Cancel)
                            return;
                        else
                        {
                            if (OnFileOpen())
                                break;
                        }
                    }
                }
            }
            oldFileName = FileName;
            MainChart.Series["Opros"].Points.Clear();
            for (int i = 0; i < DataList.Count; i++)
            {
                MainChart.Series["Opros"].Points.AddXY((Math.Round(((double)DataList[i].GetElementCount()*100.0/sum),2)).ToString()+ " %", DataList[i].GetElementCount());
                MainChart.Series["Opros"].Points[i].LegendText = DataList[i].GetElementName();
            }
            MainChart.Series["Opros"].Font = new Font(MainChart.Series["Opros"].Font.Name, 10, MainChart.Series["Opros"].Font.Style, MainChart.Series["Opros"].Font.Unit);
        }

        ToolTip toolTip = new ToolTip();
        private void BuildToolTip()
        {
            toolTip.AutoPopDelay = 5000;
            toolTip.InitialDelay = 1000;
            toolTip.ReshowDelay = 500;
            toolTip.ShowAlways = true;
        }

        private struct DataElement
        {
            String ElementName; int ElementCount;
            public DataElement(String m, int c) { ElementName = m; ElementCount = c; }
            public String GetElementName() { return ElementName; }
            public int GetElementCount() { return ElementCount; }
        }

        String FileName;
        List<DataElement> DataList = new List<DataElement>();
        String ChartName = "";
        int sum;
        private bool OnFileOpen()
        {
            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                openFileDialog.InitialDirectory =Environment.GetFolderPath(Environment.SpecialFolder.Personal);
                openFileDialog.Filter = "Text files (*.txt) |*.txt";
                openFileDialog.FilterIndex = 0;
                openFileDialog.DefaultExt = "txt";

                openFileDialog.RestoreDirectory = true;
                openFileDialog.Multiselect = false;

                if (openFileDialog.ShowDialog() != DialogResult.OK)
                    return false;

                DataList.Clear();
                FileName = openFileDialog.FileName;
                String str;
                sum = 0;
                StreamReader sr = new StreamReader(openFileDialog.FileName);
                ChartName = sr.ReadLine();
                while ((str=sr.ReadLine())!=null)
                {
                    int x = str.IndexOf(' ');
                    sum += Convert.ToInt32(str.Substring(0, x));
                    DataElement newcar = new DataElement(str.Substring(x + 1), Convert.ToInt32(str.Substring(0, x)));
                    DataList.Add(newcar);
                }
                MessageBox.Show("Входные данные загружены", "OK", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return true;
            }
        }
  
        private void BuildChart(String title)
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Title title1 = new System.Windows.Forms.DataVisualization.Charting.Title();
            this.MainChart = new System.Windows.Forms.DataVisualization.Charting.Chart();
            ((System.ComponentModel.ISupportInitialize)(this.MainChart)).BeginInit();
            this.SuspendLayout();
          
            this.MainChart.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            chartArea1.Name = "ChartArea1";
            this.MainChart.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.MainChart.Legends.Add(legend1);

            this.MainChart.Location = new System.Drawing.Point(0,20);
            this.MainChart.Name = "MainChart";
            series1.ChartArea = "ChartArea1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Pie;
            series1.Legend = "Legend1";
            series1.Name = "Opros";
            series1.YValuesPerPoint = 2;
            this.MainChart.Series.Add(series1);
            this.MainChart.Size = new System.Drawing.Size(ClientSize.Width, ClientSize.Height*8/10);
            this.MainChart.TabIndex = 0;
            this.MainChart.Text = "Chart";
            title1.Name = "title1";
            title1.Font = new Font(title1.Font.Name, 15, title1.Font.Style, title1.Font.Unit);
            title1.Text = title;
            this.MainChart.Titles.Add(title1);
         
            this.Controls.Add(this.MainChart);
            this.Name = "AppWindow";
            ((System.ComponentModel.ISupportInitialize)(this.MainChart)).EndInit();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.DataVisualization.Charting.Chart MainChart;

        private void OnHelpAbout()
        {
            MessageBox.Show(
                "Задача\n"+
"Постройте диаграмму опроса в сети\n"+
"отображающую процентное соотношение\n"+
"проголосовавших.\n\n"+
"Жиркевич Алесандр Юрьевич\n"+
"1 курс, 10 группа, ФПМИ, БГУ",
                "About...",
                MessageBoxButtons.OK,
                MessageBoxIcon.Information);
        }
    }
}
