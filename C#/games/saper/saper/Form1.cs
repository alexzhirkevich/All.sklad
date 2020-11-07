using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Media;

namespace saper
{
    
    public partial class Form1 : Form
    {
        public class Kletki {
            public class Kletka {

                public Label counter;
                private static Bitmap[] imgs = {
                    Properties.Resources._1,
                    Properties.Resources._2,
                    Properties.Resources._3,
                    Properties.Resources._4,
                    Properties.Resources._5,
                    Properties.Resources._6,
                    Properties.Resources._7,
                    Properties.Resources._8,
                };

                public Button button;
                private Kletka[,] arr;
                public Dificulty dificulty;

                public bool isMine = false;
                bool flaged = false;
                bool opened = false;

                private int size = 0;
                private int i, j, n, m;
                public int mines = 0;
              
                public Kletka(int size, int i,int j,ref Kletka[,] mass,int n,int m,Dificulty dif)
                {
                    button = new Button();
                    this.dificulty = dif;
                    arr = mass;
                    this.i = i;
                    this.j = j;
                    this.n = n;
                    this.m = m;
                    this.size = size;

                    SetBtnStyle();
                }
              
                private void SetBtnStyle()
                {
                    button.BackgroundImageLayout = ImageLayout.Zoom;
                    button.Visible = true;
                    button.Enabled = true;
                    button.FlatStyle = FlatStyle.Standard;
                    button.BackColor = Color.Gray;
                    button.Location = new Point(5 + size * i, 50 + size * j);
                    button.Size = new Size(size, size);
                    button.MouseUp += new MouseEventHandler(onClick);
                    button.GotFocus += new EventHandler(onGotFocus);
                }
                
                private void onGotFocus(object sender, EventArgs e)
                {
                    button.FindForm().ActiveControl = null;
                }

                private void onClick(object sender, MouseEventArgs args)
                {
                    if (args.Button == MouseButtons.Left)
                    {
                        if (!flaged)
                        {
                            if (isMine)
                            {
                                new SoundPlayer(Properties.Resources.boom).Play();
                                OpenAllMines();
                            }
                            else
                            {
                                Open();
                                if (CheckVictory())
                                {
                                    new SoundPlayer(Properties.Resources.victory).Play();
                                    MessageBox.Show("You won");
                                    RebuildAll();
                                }
                            }
                        }
                        else
                        {
                            RemoveFlag();
                        }
                    }
                    if (args.Button == MouseButtons.Right)
                    {
                        if (!flaged)
                        {
                            AddFlag();
                        }
                        else
                        {
                            RemoveFlag();
                        }
                    }
                }

                public void RebuildAll()
                {
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < m; j++)
                        {
                            arr[i, j].button.Enabled = true;
                            arr[i, j].button.BackColor = Color.Gray;
                            arr[i, j].button.BackgroundImage = null;
                            arr[i, j].isMine = false;
                            arr[i, j].flaged = false;
                            arr[i, j].opened = false;
                            arr[i, j].mines = 0;
                        }
                    }
                    PlaceMines();
                }

                private void OpenAllMines()
                {
                    for (int i = 0; i < n; i++)
                    {
                        for(int j = 0; j < m; j++)
                        { 
                            if (arr[i, j].isMine)
                            {
                                arr[i, j].button.BackgroundImage = Properties.Resources.bomb;
                                arr[i, j].button.BackColor = Color.White;
                            }
                        }
                    }
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < m; j++)
                        {
                            arr[i, j].button.Enabled = false;
                        }
                    }
                }

                public bool CheckVictory()
                {
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < m; j++)
                        {
                            if (arr[i, j].opened == false && arr[i, j].isMine == false)
                                return false;
                        }
                    }
                    return true;
                    
                }

                public bool Open()
                {
                    if (!opened)
                    {
                        RemoveFlag();
                        button.BackColor = Color.White;
                        if (mines > 0)
                        {
                            button.Enabled = false;
                            button.BackgroundImage = imgs[mines - 1];
                            opened = true;
                        }
                        else
                            OpenNear();
                       
                        return true;

                    }
                    return false;
                }

                public bool AddFlag()
                {
                    if (!flaged)
                    {
                        if (counter.Text == "0")
                            return false;
                        button.BackgroundImage = Properties.Resources.flag;
                        flaged = true;
                        counter.Text = (Convert.ToInt16(counter.Text) - 1).ToString();
                        return true;
                    }
                    return false;
                }

                public bool RemoveFlag()
                {
                    if (flaged)
                    {
                        button.BackgroundImage = null;
                        flaged = false;
                        counter.Text = (Convert.ToInt16(counter.Text) + 1).ToString();
                        return true;
                    }
                    return false;
                }

                public void OpenNear()
                {
                    if (!opened)
                    {
                        RemoveFlag();
                        button.Enabled = false;
                        button.BackColor = Color.White;
                        opened = true;

                        if (mines != 0)
                        {
                            button.BackgroundImage = imgs[mines - 1];
                            return;
                        }
                        if (i > 0 && j > 0 && arr[i - 1, j - 1].mines >= 0)
                            arr[i - 1, j - 1].OpenNear();
                        if (i > 0 && arr[i - 1, j].mines >= 0)
                            arr[i - 1, j].OpenNear();
                        if (i > 0 && j < m - 1 && arr[i - 1, j + 1].mines >= 0)
                            arr[i - 1, j + 1].OpenNear();
                        if (j < m - 1 && arr[i, j + 1].mines >= 0)
                            arr[i, j + 1].OpenNear();
                        if (i < n - 1 && j < m - 1 && arr[i + 1, j + 1].mines >= 0)
                            arr[i + 1, j + 1].OpenNear();
                        if (i < n - 1 && arr[i + 1, j].mines >= 0)
                            arr[i + 1, j].OpenNear();
                        if (i < n - 1 && j > 0 && arr[i + 1, j - 1].mines >= 0)
                            arr[i + 1, j - 1].OpenNear();
                        if (j > 0 && arr[i, j - 1].mines >= 0)
                            arr[i, j - 1].OpenNear();

                    }
                }

                public void PlaceMines()
                {
                    int mineCount=50;
                    if (dificulty == Dificulty.Medium)
                        mineCount = 75;
                    else
                        if (dificulty == Dificulty.Hard)
                            mineCount = 100;
                    Random r = new Random();
                    for (int i = 0; i < mineCount; i++)
                    {
                        int x = r.Next(n), y = r.Next(m);
                        while (arr[x, y].isMine)
                        {
                            x = r.Next(n);
                            y = r.Next(m);
                        }
                        arr[x, y].isMine = true;
                        arr[x, y].mines = -1;
                        counter.Text = mineCount.ToString();
                    }
                    for (int x = 0; x < n; x++)
                    {
                        for (int y = 0; y < m; y++)
                        {
                            if (!arr[x, y].isMine)
                            {
                                if (x > 0 && y > 0 && arr[x - 1, y - 1].isMine)
                                    arr[x, y].mines++;
                                if (x > 0 && arr[x - 1, y].isMine)
                                    arr[x, y].mines++;
                                if (x > 0 && y < m - 1 && arr[x - 1, y + 1].isMine)
                                    arr[x, y].mines++;
                                if (y < m - 1 && arr[x, y + 1].isMine)
                                    arr[x, y].mines++;
                                if (x < n - 1 && y < m - 1 && arr[x + 1, y + 1].isMine)
                                    arr[x, y].mines++;
                                if (x < n - 1 && arr[x + 1, y].isMine)
                                    arr[x, y].mines++;
                                if (x < n - 1 && y > 0 && arr[x + 1, y - 1].isMine)
                                    arr[x, y].mines++;
                                if (y > 0 && arr[x, y - 1].isMine)
                                    arr[x, y].mines++;
                            }
                        }
                    }
                }
            }

            private Kletka[,] array;
            private int n, m;
            private Label counter;
            private Dificulty dificulty;

            public Kletki(int n, int m, int size,ref Label counter,Dificulty dif)
            {
                this.counter = counter;
                this.n = n;
                this.m = m;
                this.dificulty = dif;
                array = new Kletka[n, m];
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < m; j++)
                    {
                        array[i, j] = new Kletka(size,i,j,ref array,n,m,dif);
                        array[i, j].counter = counter;
                    }
                }
                array[0, 0].PlaceMines();

            }

            public void SetDifucilty(Dificulty dificulty)
            {
                this.dificulty = dificulty;
                for (int i = 0; i < n; i++)
                {
                    for(int j = 0; j < m; j++)
                    {
                        array[i, j].dificulty = dificulty;
                    }
                }
                array[0, 0].RebuildAll();
            }

            public Control[] GetControls()
            {
                Control[] c = new Control[n * m];
                int cnt = 0;
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < m; j++)
                    {
                        c[cnt++] = array[i, j].button;
                    }
                }
                
                return c;
            }
        }

        public enum Dificulty { Easy, Medium, Hard };

        const int kletka = 30;
        Kletki kletki;
        Dificulty dificulty = Dificulty.Easy;
        const int n=22, m=22;

        public Form1()
        {
            InitializeComponent();
            kletki = new Kletki(n,m, kletka, ref counter, dificulty);
            Controls.AddRange(kletki.GetControls());
        }

        private void button1_MouseDown(object sender, MouseEventArgs e)
        {
            button1.BackgroundImage = Properties.Resources.x_mark_pressed;
        }

        private void button1_MouseUp(object sender, MouseEventArgs e)
        {
            button1.BackgroundImage = Properties.Resources.x_mark;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            WindowState = FormWindowState.Minimized;
        }

        private void button2_MouseDown(object sender, MouseEventArgs e)
        {
            button2.BackgroundImage = Properties.Resources.minimize_pressed;
        }

        private void button2_MouseUp(object sender, MouseEventArgs e)
        {
            button2.BackgroundImage = Properties.Resources.minimize;
        }

        Point lastPoint;

        private void panel1_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                Left += e.X - lastPoint.X;
                Top += e.Y - lastPoint.Y;
            }
        }

        private void panel1_MouseDown(object sender, MouseEventArgs e)
        {
            lastPoint = new Point(e.X, e.Y);
        }


        private void Form1_Shown(object sender, EventArgs e)
        {
            new SoundPlayer(Properties.Resources.start).Play();
        }

        private void easyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dificulty == Dificulty.Easy)
                return;
            dificulty = Dificulty.Easy;
            easyToolStripMenuItem.Checked = true;
            meduimToolStripMenuItem.Checked = false;
            hardToolStripMenuItem.Checked = false;
            UpdateDiffuculty();
        }

        private void meduimToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dificulty == Dificulty.Medium)
                return;
            dificulty = Dificulty.Medium;
            easyToolStripMenuItem.Checked = false;
            meduimToolStripMenuItem.Checked = true;
            hardToolStripMenuItem.Checked = false;
            UpdateDiffuculty();
        }

        private void hardToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dificulty == Dificulty.Hard)
                return;
            dificulty = Dificulty.Hard;
            easyToolStripMenuItem.Checked = false;
            meduimToolStripMenuItem.Checked = false;
            hardToolStripMenuItem.Checked = true;
            UpdateDiffuculty();
        }
        private void UpdateDiffuculty()
        {
            kletki.SetDifucilty(dificulty);
            new SoundPlayer(Properties.Resources.start).Play();
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            UpdateDiffuculty();
        }

        private void pictureBox2_MouseDown(object sender, MouseEventArgs e)
        {
            pictureBox2.BackgroundImage = Properties.Resources.restart_pressed;
        }

        private void pictureBox2_MouseUp(object sender, MouseEventArgs e)
        {
            pictureBox2.BackgroundImage = Properties.Resources.restart;
        }
    }
}
