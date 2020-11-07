using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace лаб_2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            MaximumSize = new Size(300, 370);
            MinimumSize = new Size(300, 370);
            inputFileDialog.FileName = "";
            outFileDialog.FileName = "";
        }
     
        const int KeySize = 128;
        const int BlockSize = 512;
        OpenFileDialog inputFileDialog = new OpenFileDialog();
        SaveFileDialog outFileDialog = new SaveFileDialog();
        byte[] key = new byte[KeySize];
        byte[] revKey = new byte[KeySize];
        Boolean keyIsLoaded = false;
        Boolean FileIsShifered = false;

        /*=========================================================================================*/
        
        private void ChoooseFileStrip_click(object sender, EventArgs e)
        {
            inputFileDialog.ShowDialog();
        }

        /*=========================================================================================*/

        private void GenerateKey(ref byte[] key, int count)
        {
            Random rand = new Random();
            for (int i = 0; i < count; i++)
                key[i] = (byte)rand.Next();
        }

        /*=========================================================================================*/

        private void WriteKeyToFile(ref byte[] key, int count)
        {
            if (!FileIsShifered)
            {
                MessageBox.Show("Вы ещё не зашифровали файл", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            MessageBox.Show("Выберите, куда сохранить ключ");
            SaveFileDialog keyFileDialog = new SaveFileDialog();
            keyFileDialog.FileName = "Key.bin";
        selectKeyFIle:
            {
                if (keyFileDialog.ShowDialog() != DialogResult.OK)
                    if (MessageBox.Show("Вы уверены, что не хотите сохранить ключ?", "Подтвердите действие", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
                        goto selectKeyFIle;
            }
            BinaryWriter kf = new BinaryWriter(File.Open(keyFileDialog.FileName, FileMode.OpenOrCreate));
            kf.Write(key, 0, count);
            kf.Close();
        }

        /*=========================================================================================*/

        private void MakeShifr(ref byte[] key, int count)
        {
            BinaryWriter output = new BinaryWriter(File.Open(outFileDialog.FileName, FileMode.OpenOrCreate));
            BinaryReader input = new BinaryReader(File.Open(inputFileDialog.FileName, FileMode.Open));

            byte[] block = new byte[BlockSize];

            while (input.Read(block, 0, BlockSize) != 0)
            {
                int num = 0;
                for (int i = 0; i < BlockSize; i++)
                {
                    block[i] = (byte)(block[i] ^ key[num]);
                    if (++num == KeySize)
                        num = 0;
                }
                output.Write(block, 0, BlockSize);
            }

            if (input.BaseStream.Position != input.BaseStream.Length)
            {
                int lastLength = (int)(input.BaseStream.Length - input.BaseStream.Position);
                byte[] lastblock = new byte[lastLength];
                input.Read(lastblock, 0, lastLength);
                int num = 0;
                for (int i = 0; i < lastLength; i++)
                {
                    lastblock[i] = (byte)(lastblock[i] ^ key[num]);
                    if (++num == KeySize)
                        num = 0;
                }
                output.Write(lastblock, 0, lastLength);
            }

            FileIsShifered = true;
            output.Close();
            input.Close();
        }

        /*=========================================================================================*/

        private void btnShifr_click(object sender, EventArgs e)
        {
            if (inputFileDialog.FileName == "" && inputFileDialog.ShowDialog() != DialogResult.OK)
                return;

            MessageBox.Show("Выберите, куда сохранить зашифрованный файл");
            if (outFileDialog.FileName == "" && outFileDialog.ShowDialog() != DialogResult.OK)
                return;

            if (outFileDialog.FileName == inputFileDialog.FileName)
            {
                MessageBox.Show("Невозможно перезаписать файл", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            GenerateKey(ref key, KeySize);

            MakeShifr(ref key, KeySize);

            btnGetKey.Enabled = true;

            inputFileDialog.FileName = "";
            outFileDialog.FileName = "";

            MessageBox.Show("Файл зашифрован. Не забудьте сохранить ключ");
        }

        /*=========================================================================================*/

        private void btnWriteKey_click(object sender, EventArgs e)
        {
            WriteKeyToFile(ref key, KeySize);
        }

        /*=========================================================================================*/

        private void btnGetKey_click(object sender, EventArgs e)
        {
            OpenFileDialog GetKeyDialog = new OpenFileDialog();
            if (GetKeyDialog.ShowDialog() != DialogResult.OK)
                return;
            BinaryReader keyReader = new BinaryReader(File.Open(GetKeyDialog.FileName, FileMode.Open));
            keyReader.Read(revKey, 0, KeySize);
            keyIsLoaded = true;
            keyReader.Close();
            btnDeShifr.Enabled = true;
        }

        /*=========================================================================================*/

        private void btnDeShifr_click(object sender, EventArgs e)
        {
            if (!keyIsLoaded)
            {
                MessageBox.Show("Ключ не загружен", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (inputFileDialog.FileName == "" && inputFileDialog.ShowDialog() != DialogResult.OK)
                return;

            MessageBox.Show("Выберите, куда сохранить расшифрованный файл");
            if (outFileDialog.FileName == "" && outFileDialog.ShowDialog() != DialogResult.OK)
                return;

            if (outFileDialog.FileName==inputFileDialog.FileName)
            {
                MessageBox.Show("Невозможно перезаписать файл", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            MakeShifr(ref revKey, KeySize);

            inputFileDialog.FileName = "";
            outFileDialog.FileName = "";

            MessageBox.Show("Файл расшифрован");
        }
    }
}