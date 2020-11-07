using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using System.Media;

namespace krestiki_noliki
{
    public partial class Form1 : Form
    { 
        private enum Cell { Krestik, Nolik, Empty }
        private enum Result { Won, Lost, None, Continue }
        private static string won =     "You won";
        private static string lost =    "You lost";
        private static string none =    "A tie";
        private static int you = 0;
        private static int comp = 0;

        Dictionary<Panel, Cell> cells = new Dictionary<Panel, Cell>();
        Panel[] panels = new Panel[9];
        SoundPlayer sTic = new SoundPlayer(Properties.Resources.tic);
        SoundPlayer sRestart = new SoundPlayer(Properties.Resources.restart_sound);
        SoundPlayer sLose = new SoundPlayer(Properties.Resources.lose);
        SoundPlayer sWon = new SoundPlayer(Properties.Resources.tada);

        public Form1()
        {
            InitializeComponent();
            sTic.LoadAsync();
            sRestart.LoadAsync();
            sLose.LoadAsync();
            sWon.LoadAsync();
            ResultLable.Text = null;
            ScoreBoard.Text = Score(Result.None);
            int idx = 0;
            panels[idx++] = TopLeft;
            panels[idx++] = Top;
            panels[idx++] = TopRight;
            panels[idx++] = Left;
            panels[idx++] = Center;
            panels[idx++] = Right;
            panels[idx++] = BottomLeft;
            panels[idx++] = Bottom;
            panels[idx++] = BottomRight;
            for (int i = 0;i< 9; i++)
            {
                cells.Add(panels[i], Cell.Empty);
            }
        }

        private string Score(Result result)
        {
            if (result == Result.Won)
                return $"You {++you} : {comp} Comp";
            if (result == Result.Lost)
                return $"You {you} : {++comp} Comp";
            else
                return $"You {you} : {comp} Comp";
        }   

        private void PaintRed(int a,int b,int c)
        {
            panels[a].BackColor = Color.Red;
            panels[b].BackColor = Color.Red;
            panels[c].BackColor = Color.Red;
        }
        
        private void onClick(object sender, MouseEventArgs args)
        {
            Panel panel = (Panel)sender;
            panel.BackColor = Color.Azure;
            if (cells[panel] == Cell.Empty)
            {
                panel.BackgroundImage = Properties.Resources.krestik;
                cells[panel] = Cell.Krestik;
                sTic.Play();
                if (EndMessage(CheckEnd()) == Result.Continue)
                {
                    CheckLines();
                    EndMessage(CheckEnd());
                }
            }
        }
        
        private void CheckLines()
        {
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[1]] == Cell.Nolik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[2]] == Cell.Nolik &&
                cells[panels[1]] == Cell.Empty)
            {
                SetValue(1, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Nolik &&
                cells[panels[2]] == Cell.Nolik &&
                cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[5]] == Cell.Empty)
            {
                SetValue(5, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Nolik &&
                cells[panels[5]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Nolik &&
                cells[panels[5]] == Cell.Nolik &&
                cells[panels[3]] == Cell.Empty)
            {
                SetValue(3, Cell.Nolik);
                return;
            }
            if (cells[panels[6]] == Cell.Nolik &&
                cells[panels[7]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[6]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik &&
                cells[panels[7]] == Cell.Empty)
            {
                SetValue(7, Cell.Nolik);
                return;
            }
            if (cells[panels[7]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik &&
                cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Nolik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Nolik &&
               cells[panels[3]] == Cell.Nolik &&
               cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Nolik &&
               cells[panels[6]] == Cell.Nolik &&
               cells[panels[3]] == Cell.Empty)
            {
                SetValue(3, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Nolik &&
               cells[panels[6]] == Cell.Nolik &&
               cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Nolik &&
               cells[panels[4]] == Cell.Nolik &&
               cells[panels[7]] == Cell.Empty)
            {
                SetValue(7, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Nolik &&
               cells[panels[7]] == Cell.Nolik &&
               cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Nolik &&
               cells[panels[7]] == Cell.Nolik &&
               cells[panels[1]] == Cell.Empty)
            {
                SetValue(1, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Nolik &&
               cells[panels[5]] == Cell.Nolik &&
               cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Nolik &&
               cells[panels[8]] == Cell.Nolik &&
               cells[panels[5]] == Cell.Empty)
            {
                SetValue(5, Cell.Nolik);
                return;
            }
            if (cells[panels[5]] == Cell.Nolik &&
               cells[panels[8]] == Cell.Nolik &&
               cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }

            ///////////////////////////////////////////////
            
            if (cells[panels[0]]==Cell.Krestik &&
                cells[panels[1]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Krestik &&
                cells[panels[1]] == Cell.Empty)
            {
                SetValue(1, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Krestik &&
                cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Empty)
            {
                SetValue(5, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik &&
                cells[panels[3]] == Cell.Empty)
            {
                SetValue(3, Cell.Nolik);
                return;
            }
            if (cells[panels[6]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[6]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Empty)
            {
                SetValue(7, Cell.Nolik);
                return;
            }
            if (cells[panels[7]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik &&
                cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Krestik &&
               cells[panels[3]] == Cell.Krestik &&
               cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }
            if (cells[panels[0]] == Cell.Krestik &&
               cells[panels[6]] == Cell.Krestik &&
               cells[panels[3]] == Cell.Empty)
            {
                SetValue(3, Cell.Nolik);
                return;
            }
            if (cells[panels[3]] == Cell.Krestik &&
               cells[panels[6]] == Cell.Krestik &&
               cells[panels[0]] == Cell.Empty)
            {
                SetValue(0, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Krestik &&
               cells[panels[4]] == Cell.Krestik &&
               cells[panels[7]] == Cell.Empty)
            {
                SetValue(7, Cell.Nolik);
                return;
            }
            if (cells[panels[1]] == Cell.Krestik &&
               cells[panels[7]] == Cell.Krestik &&
               cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }
            if (cells[panels[4]] == Cell.Krestik &&
               cells[panels[7]] == Cell.Krestik &&
               cells[panels[1]] == Cell.Empty)
            {
                SetValue(1, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Krestik &&
               cells[panels[5]] == Cell.Krestik &&
               cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }
            if (cells[panels[2]] == Cell.Krestik &&
               cells[panels[8]] == Cell.Krestik &&
               cells[panels[5]] == Cell.Empty)
            {
                SetValue(5, Cell.Nolik);
                return;
            }
            if (cells[panels[5]] == Cell.Krestik &&
               cells[panels[8]] == Cell.Krestik &&
               cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }

            //////////////////////////////////////////

            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }

            if (cells[panels[7]]==Cell.Krestik &&
                cells[panels[5]]==Cell.Krestik &&
                cells[panels[2]]==Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }

            if (cells[panels[4]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }

            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }

            if (cells[panels[7]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Empty)
            {
                SetValue(2, Cell.Nolik);
                return;
            }

            if (cells[panels[5]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Empty)
            {
                SetValue(8, Cell.Nolik);
                return;
            }

            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Empty)
            {
                SetValue(6, Cell.Nolik);
                return;
            }

            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik &&
                cells[panels[1]] == Cell.Empty)
            {
                SetValue(1, Cell.Nolik);
                return;
            }

            if (cells[panels[4]] == Cell.Empty)
            {
                SetValue(4, Cell.Nolik);
                return;
            }

            for (int i = 0; i < 9; i++)
            {
                if (cells[panels[i]] == Cell.Empty)
                {
                    SetValue(i, Cell.Nolik);
                    return;
                }
            }
        }

        private void SetValue(int number, Cell value)
        {
            cells[panels[number]] = value;
            if (value == Cell.Krestik)
            {
                panels[number].BackgroundImage = Properties.Resources.krestik;
                panels[number].Enabled = false;
                return;
            }
            if (value == Cell.Nolik)
            {
                panels[number].BackgroundImage = Properties.Resources.nolik;
                panels[number].Enabled = false;
                return;
            }
            if (value == Cell.Empty)
            {
                panels[number].BackgroundImage = null;
                return;
            }
        }

        private void RestorePanels()
        {
            for (int i = 0; i < 9; i++)
            {
                SetValue(i, Cell.Empty);
                panels[i].BackColor = Color.Azure;
                panels[i].Enabled = true;
            }
            sRestart.Play();
        }

        private void DisableCells()
        {
            for (int i =0; i<9; i++)
            {
                panels[i].Enabled = false;
            }
        }

        private Result EndMessage(Result result)
        {
            if (result == Result.Won)
            {
                sWon.Play();
                ScoreBoard.Text = Score(result);
                ResultLable.Text = won;
            }
            else
                if(result == Result.Lost)
            {
                ScoreBoard.Text = Score(result);
                ResultLable.Text = lost;
                sLose.Play();
            }
            else
                if (result == Result.None)
            {
                ResultLable.Text = none;
            }
            if (result != Result.Continue)
                DisableCells();
            return result;
        }

        private Result CheckWin()
        {
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[1]] == Cell.Krestik &&
                cells[panels[2]] == Cell.Krestik)
            { 
                PaintRed(0,1,2);
                return Result.Won;
            }
            if (cells[panels[3]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik)
            {
                PaintRed(3,4,5);
                return Result.Won;
            }
            if (cells[panels[6]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik)
            {
                PaintRed(6,7,8);
                return Result.Won;
            }
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik)
            {
                PaintRed(0,4,8);
                return Result.Won;
            }
            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik)
            {
                PaintRed(2,4,6);
                return Result.Won;
            }
            if (cells[panels[0]] == Cell.Krestik &&
                cells[panels[3]] == Cell.Krestik &&
                cells[panels[6]] == Cell.Krestik)
            {
                PaintRed(0,3,6);
                return Result.Won;
            }
            if (cells[panels[1]] == Cell.Krestik &&
                cells[panels[4]] == Cell.Krestik &&
                cells[panels[7]] == Cell.Krestik)
            {
                PaintRed(1,4,7);
                return Result.Won;
            }
            if (cells[panels[2]] == Cell.Krestik &&
                cells[panels[5]] == Cell.Krestik &&
                cells[panels[8]] == Cell.Krestik)
            {
                PaintRed(2,5,8);
                return Result.Won;
            }
            return Result.Continue;
        }

        private Result CheckLose()
        {
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[1]] == Cell.Nolik &&
                cells[panels[2]] == Cell.Nolik)
            {
                PaintRed(0,1,2);
                return Result.Lost;
            }
            if (cells[panels[3]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[5]] == Cell.Nolik)
            {
                PaintRed(3,4,5);
                return Result.Lost;
            }
            if (cells[panels[6]] == Cell.Nolik &&
                cells[panels[7]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik)
            {
                PaintRed(6,7,8);
                return Result.Lost;
            }
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik)
            {
                PaintRed(0,4,8);
                return Result.Lost;
            }
            if (cells[panels[2]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Nolik)
            {
                PaintRed(2,4,6);
                return Result.Lost;
            }
            if (cells[panels[0]] == Cell.Nolik &&
                cells[panels[3]] == Cell.Nolik &&
                cells[panels[6]] == Cell.Nolik)
            {
                PaintRed(0,3,6);
                return Result.Lost;
            }
            if (cells[panels[1]] == Cell.Nolik &&
                cells[panels[4]] == Cell.Nolik &&
                cells[panels[7]] == Cell.Nolik)
            {
                PaintRed(1,4,7);
                return Result.Lost;
            }
            if (cells[panels[2]] == Cell.Nolik &&
                cells[panels[5]] == Cell.Nolik &&
                cells[panels[8]] == Cell.Nolik)
            {
                PaintRed(2,5,8);
                return Result.Lost;
            }
            return Result.Continue;
        }

        private Result CheckEnd()
        {
            if (CheckWin() == Result.Won)
                return Result.Won;
            if (CheckLose() == Result.Lost)
                return Result.Lost;
            bool end = true;
            for (int i = 0; i < 9; i++)
            {
                if (cells[panels[i]] == Cell.Empty)
                    end = false;
            }
            if (end)
                return Result.None;
            else
                return Result.Continue;
        }

        private void Cell_MouseEnter(object sender, EventArgs e)
        {
            Panel panel = (Panel)sender;
            if (cells[panel] == Cell.Empty)
            {
                panel.BackColor = Color.LightCyan;
            }
        }

        private void Cell_MouseLeave(object sender, EventArgs e)
        {
            Panel panel = (Panel)sender;
            if (cells[panel] == Cell.Empty)
            {
                panel.BackColor = Color.Azure;
            }
        }

        private void button1_MouseDown(object sender, MouseEventArgs e)
        {
            btnClose.BackgroundImage = Properties.Resources.x_mark_pressed;
        }

        private void button1_MouseUp(object sender, MouseEventArgs e)
        {
            btnClose.BackgroundImage = Properties.Resources.x_mark;
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
            btnMinimize.BackgroundImage = Properties.Resources.minimize_pressed;
        }

        private void button2_MouseUp(object sender, MouseEventArgs e)
        {
            btnMinimize.BackgroundImage = Properties.Resources.minimize;
        }

        Point lastPoint;

        private void Form_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                base.Left += e.X - lastPoint.X;
                base.Top += e.Y - lastPoint.Y;
            }
        }

        private void Form_MouseDown(object sender, MouseEventArgs e)
        {
            lastPoint = new Point(e.X, e.Y);
        }

        private void Restart_MouseDown(object sender, MouseEventArgs e)
        {
            ((Control)sender).BackgroundImage = Properties.Resources.restart_pressed;
        }

        private void Restart_MouseUp(object sender, MouseEventArgs e)
        {
            ((Control)sender).BackgroundImage = Properties.Resources.restart;
        }

        private void Restart_Click(object sender, EventArgs e)
        {
            RestorePanels();
            ResultLable.Text = null;
        }

        private void Cell_MouseDown(object sender, MouseEventArgs e)
        {
            Panel panel = (Panel)sender;
            if (cells[panel] == Cell.Empty)
                panel.BackColor = Color.PowderBlue;
        }

        private void Cell_MouseUp(object sender, MouseEventArgs e)
        {
            Panel panel = (Panel)sender;
            if (cells[panel]== Cell.Empty)
                panel.BackColor = Color.Azure;
        }

        private void Restart_MouseEnter(object sender, EventArgs e)
        {
            Restart.BackgroundImage = Properties.Resources.restart_hover;
        }

        private void Restart_MouseLeave(object sender, EventArgs e)
        {
            Restart.BackgroundImage = Properties.Resources.restart;
        }
    }
}
