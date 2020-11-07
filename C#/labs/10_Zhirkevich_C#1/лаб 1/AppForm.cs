using System;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace лаб_1
{
    public partial class AppWindow : Form
    {
        public AppWindow()
        {
            FormClosing += AppWindow_FormClosing;
            Resize += AppWindow_Resize;
            MinimumSize = new Size(600, 400);
            InitializeComponent();
            AppWindow_Resize(null,null);
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog.FileName = "";
            openFileDialog.Filter = "Text files|*"; // забыл звездочку
            openFileDialog.ShowDialog();
        }

        private void AppWindow_FormClosing(Object sender, FormClosingEventArgs e)
        {
            if (MessageBox.Show("Вы уверены, что хотите выйти?", Text, MessageBoxButtons.YesNo, MessageBoxIcon.Question) != DialogResult.Yes)
            {
                e.Cancel = true;
            }
        }

        private void AppWindow_Resize(object sender, System.EventArgs e)
        {
            if (btnIncoding != null)
            {
                btnIncoding.SetBounds(
                   (ClientSize.Width - btnIncoding.Width) / 2,
                   (ClientSize.Height - btnIncoding.Height) / 2 + InpIncoding.Height,
                   btnIncoding.Width,
                   btnIncoding.Height);

                InpIncoding.SetBounds(
                   (ClientSize.Width - InpIncoding.Width) / 2 - InpIncoding.Width / 2 - 30,
                   (ClientSize.Height - InpIncoding.Height) / 2,
                    InpIncoding.Width,
                     InpIncoding.Height);

                OutIncoding.SetBounds(
                   (ClientSize.Width - OutIncoding.Width) / 2 + OutIncoding.Width / 2 + 30,
                   (ClientSize.Height - OutIncoding.Height) / 2,
                    OutIncoding.Width,
                     OutIncoding.Height);

                InpLabel.SetBounds(
                   (ClientSize.Width - InpLabel.Width) / 2 - InpLabel.Width/2 - 20,
                 (ClientSize.Height - InpLabel.Height) / 2 - InpIncoding.Height + 20,
                  InpLabel.Width,
                   InpLabel.Height);

                OutLabel.SetBounds(
                     (ClientSize.Width - OutLabel.Width) / 2 + OutLabel.Width / 2 + 20,
                   (ClientSize.Height - OutLabel.Height) / 2 - OutIncoding.Height + 20,
                    OutLabel.Width,
                     OutLabel.Height);
            }
        }

        private void btnIncoding_Click(object sender, EventArgs e)
        {
            if (openFileDialog.FileName == "")
                if (openFileDialog.ShowDialog() != DialogResult.OK)
                    return;
            if (InputEncoding == null)
            {
                MessageBox.Show("Выберите входную кодировку", "Info", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            if (OutputEncoding == null)
            {
                MessageBox.Show("Выберите выходную кодировку","Info",MessageBoxButtons.OK,MessageBoxIcon.Information);
                return;
            }
            if (InputEncoding == OutputEncoding)
            {
                MessageBox.Show("Входная и выходная кодировки совпадают", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            File.WriteAllText(openFileDialog.FileName, File.ReadAllText(openFileDialog.FileName, InputEncoding), OutputEncoding);
            MessageBox.Show("Кодировка файла изменена","OK",MessageBoxButtons.OK,MessageBoxIcon.Information);
        }

        private void SelectInpIncoding(object sender, EventArgs e)
        {
            if (InpIncoding.GetSelected(0))
                InputEncoding = Encoding.GetEncoding("koi8-r");
            if (InpIncoding.GetSelected(1))
                InputEncoding = Encoding.GetEncoding(1251);
            if (InpIncoding.GetSelected(2))
                InputEncoding = Encoding.GetEncoding("utf-8");
            if (InpIncoding.GetSelected(3))
                InputEncoding = Encoding.GetEncoding("cp866");
        }

        private void SelectOutIncoding(object sender, EventArgs e)
        {
            if (OutIncoding.GetSelected(0))
                OutputEncoding = Encoding.GetEncoding("koi8-r");
            if (OutIncoding.GetSelected(1))
                OutputEncoding = Encoding.GetEncoding(1251);
            if (OutIncoding.GetSelected(2))
                OutputEncoding = Encoding.GetEncoding("utf-8");
            if (OutIncoding.GetSelected(3))
                OutputEncoding = Encoding.GetEncoding("cp866");
        }
    }
}
