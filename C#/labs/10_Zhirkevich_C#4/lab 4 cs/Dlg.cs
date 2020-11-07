using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab_4_cs
{
    public partial class Dlg : Form
    {
        public Dlg()
        {
            InitializeComponent();
            this.CenterToScreen();
        }

        private void btnOk(object sender, EventArgs e)
        {
            try
            {
                if (this.textBox1.Text == "" || this.textBox2.Text == "")
                {
                    MessageBox.Show("Введите число разделений", "Error",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                else
                {
                    AppWindow.widthCnt = Convert.ToInt16(this.textBox1.Text);
                    AppWindow.heightCnt = Convert.ToInt16(this.textBox2.Text);
                    this.Close();
                }
            }
            catch
            {
                MessageBox.Show("Введите число разделений", "Error",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
