using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.CodeDom;
using System.Data.SqlTypes;
using System.Reflection;
using System.Text;
using AForge.DebuggerVisualizers;
using AForge.Video;
using AForge.Video.DirectShow;
using System.Media;
using AForge.Controls;
using System.Drawing;

namespace cam
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            Bitmap screen;
            while ((screen = videoSourcePlayer.GetCurrentVideoFrame()) == null) ;
        }

    }
}
