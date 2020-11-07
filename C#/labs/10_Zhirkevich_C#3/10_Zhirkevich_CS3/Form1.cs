using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace _10_Zhirkevich_CS3
{
    public partial class Form1 : Form
    {
        private OpenFileDialog openFileDialog = new OpenFileDialog();
        private SaveFileDialog saveFileDialog = new SaveFileDialog();
        private List<String> words = new List<string>();
        public Form1()
        {
            InitializeComponent();
            MinimumSize = new Size(this.Width, this.Height);
            MaximumSize = new Size(this.Width, this.Height);
        }

        private void Compare(ref List<String> list, uint frst, uint scnd)
        {
            if (list[(int)scnd].ToLower().CompareTo(list[(int)frst].ToLower()) < 0)
            {
                String temp = list[(int)scnd];
                list[(int)scnd] = list[(int)frst];
                list[(int)frst] = temp;
            }
        }
        void Shuffle(ref List<String> list, uint left, uint right)
        {
            uint half = (left + right) / 2;
            List<String> tmp = new List<String>(list);
            for (uint i = left, j = 0; i <= right; i += 2, j++)
            {
                tmp[(int)i] = list[(int)(left + j)];
                tmp[(int)(i + 1)] = list[(int)(half + j + 1)];
            }
            for (uint i = 0; i < tmp.Count(); i++)
                list[(int)i] = tmp[(int)i];

        }
        void Unshuffle(ref List<String> list, uint left, uint right)
        {
            uint half = (left + right) / 2;
            List<String> tmp = new List<String>(list);
            for (uint i = left, j = 0; i <= right; i += 2, j++)
            {
                tmp[(int)(left + j)] = list[(int)i];
                tmp[(int)(half + j + 1)] = list[(int)(i + 1)];
            }
            for (uint i = 0; i < tmp.Count(); i++)
                list[(int)i] = tmp[(int)i];
        }
        void Sort(ref List<String> list, uint left, uint right)
        {
            if (right == left + 1)
                Compare(ref list,left,right);

            if (right < left + 2)
                return; 

            Unshuffle(ref list, left, right);

            Sort(ref list, left, (left + right) / 2);
            Sort(ref list, (left + right) / 2 + 1, right); 

            Shuffle(ref list, left, right); 

            for (uint i = left + 1; i < right; i += 2)
                Compare(ref list, i, i + 1);

            for (uint i = left + 1; i + (right - left + 1) / 2 - 1 < right; i++) 
                Compare(ref list,i, i + (right - left + 1) / 2 - 1);
        }
        private void OpenFileMenuStrip(object sender, EventArgs e)
        {
            if (openFileDialog.ShowDialog() != DialogResult.OK)
                return;
            StreamReader sr = new StreamReader(openFileDialog.FileName);
            String line;
            words.Clear();
            while ((line = sr.ReadLine()) != null)
            {
                String[] wrds = line.Split();
                for (int i = 0; i < wrds.Count(); i++)
                {
                    words.Add(wrds[i]);
                }
            }
            sr.Close();
        }

        private void SaveFileMenuStrip(object sender, EventArgs e)
        {
            if (saveFileDialog.ShowDialog() != DialogResult.OK)
                return;
            StreamWriter sw = new StreamWriter(saveFileDialog.FileName);
            for (int i = 0; i < words.Count(); i++)
            {
                sw.WriteLine(words[i]);
            }
            sw.Close();
        }

        private void ExitMenuStrip(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void SortBtnOnClick(object sender, EventArgs e)
        {
            if (words.Count() == 0)
            {
                MessageBox.Show("Файл пуст или не выбран", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            Sort(ref words, 0, (uint)(words.Count() - 1));
        }
    }
}
