using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace sea_battle
{
    public partial class Form1 : Form
    {

        public class BattleField
        {
            public class Ships
            {
                public class Ship
                {
                    public Panel[] panel;
                    public int count = 0, size = 0, number = 0;
                    public bool moving=false;
                    public bool set=false;
                    public Ship(int count, int size)
                    {
                        this.count = count;
                        this.size = size;
                        panel = new Panel[count];
                        for (int j = 0; j < count; j++)
                            panel[j] = new Panel()
                            {
                                Size = new Size(size, size),
                                BorderStyle = BorderStyle.FixedSingle,
                                BackColor = Color.SandyBrown,
                                Cursor = Cursors.Hand
                            };
                    }
                    public void SetLocation(int x,int y)
                    {
                        for (int i = 0; i < count; i++)
                        {
                            panel[i].Location = new Point(x + size * i,y);
                        }
                    }
                }
                public Ship[] ships;
                public int count, cellSize, size;

                public Ships(int size,int cellSize, int x,int y)
                {
                    count = 5-size;
                    this.cellSize = size;
                    this.size = 5- count;
                    ships = new Ship[count];
                    for (int i = 0; i < count; i++)
                    {
                        for (int j = 0; j < size; j++)
                        {
                            ships[i] = new Ship(size, cellSize);
                            ships[i].SetLocation(x + cellSize * (size * i + j) , y + cellSize * i);
                        }
                    }
                }
            }

            Panel[,] array;
            int size;
            int cellSize;
            Panel field;
            Ships ship4, ship3, ship2, ship1;

            public BattleField(int size, ref Panel field)
            {
                this.size = size;
                this.field = field;
                field.Visible = false;
                cellSize = field.Width / size;

                array = new Panel[size, size];
                FieldInit();

                ShipInit();
            }

            private void ShipInit()
            {
                ship4 = new Ships(4, cellSize, field.Right, field.Top);
                ship3 = new Ships(3, cellSize, field.Right, field.Top);
                ship2 = new Ships(2, cellSize, field.Right, field.Top);
                ship1 = new Ships(1, cellSize, field.Right, field.Top);
            }

            private void FieldInit()
            {
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        array[i, j] = new Panel()
                        {
                            Location = new Point(field.Location.X + cellSize * i, field.Location.Y + cellSize * j),
                            Size = new Size(cellSize, cellSize),
                            BackColor = Color.LightCyan,
                            BorderStyle = BorderStyle.FixedSingle,
                            Cursor = Cursors.Cross,
                            Visible = true
                        };
                    }
                }
            }

            public void SetVisible(bool visible)
            {
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        array[i, j].Visible = visible;
                    }
                }

            }

            public Control[] GetControls()
            {
                List<Control> c = new List<Control>();
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        c.Add(array[i, j]);
                    }
                }
                for (int i = 0; i < ship4.count; i++)
                    for (int j = 0;j<ship4.size;j++)
                        c.Add(ship4.ships[i].panel[j]);

                for (int i = 0; i < ship3.count; i++)
                    for (int j = 0; j < ship3.size; j++)
                        c.Add(ship3.ships[i].panel[j]);

                for (int i = 0; i < ship2.count; i++)
                    for (int j = 0; j < ship2.size; j++)
                        c.Add(ship2.ships[i].panel[j]);

                for (int i = 0; i < ship1.count; i++)
                    for (int j = 0; j < ship1.size; j++)
                        c.Add(ship1.ships[i].panel[j]);
                return c.ToArray();
            }
        }

        const int bfSize = 10;
        BattleField firstBF, secondBF;

        public Form1()
        {
            InitializeComponent();
            firstBF = new BattleField(bfSize, ref field1);
            secondBF = new BattleField(bfSize, ref field2);
            secondBF.SetVisible(false);
            Controls.AddRange(firstBF.GetControls());
            Controls.AddRange(secondBF.GetControls());
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
    }
}