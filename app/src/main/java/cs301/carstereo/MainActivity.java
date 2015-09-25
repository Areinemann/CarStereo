package cs301.carstereo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, View.OnLongClickListener
{
    private ToggleButton togglePower;
    private Button previous;
    private Button rewind;
    private Button playPause;
    private Button fastForward;
    private Button skipForward;
    private Button FM;
    private Button AM;
    private TextView text;
    private Button scanForward;
    private Button scanBack;
    private Button preset1;
    private Button preset2;
    private Button preset3;
    private Button preset4;
    private Button preset5;
    private Button preset6;



    private int AMStation = 530;
    private final int AMInc = 10;
    private boolean AMFM = true;//true if AM
    private int FMDec =1;
    private int FMStation = 88;
    private final double FMInc = 2;

    private int[] AMPreset = {550,600,650, 700, 750, 800};
    private int[] FMPreset = {90, 92, 94, 96, 98, 100};
    private int[] FMDecPreset = {9,9,9,9,9,9};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        togglePower = (ToggleButton) findViewById(R.id.powerButton);
        previous    = (Button) findViewById(R.id.SkipBack);
        rewind      = (Button) findViewById(R.id.Rewind);
        playPause   = (Button) findViewById(R.id.PausePlay);
        fastForward = (Button) findViewById(R.id.FastForward);
        skipForward = (Button) findViewById(R.id.SkipForward);

        AM          = (Button) findViewById(R.id.AMButton);
        FM          = (Button) findViewById(R.id.FMButton);

        text        = (TextView) findViewById(R.id.StationDisplay);

        scanForward = (Button) findViewById(R.id.ForwardSeek);
        scanBack    = (Button) findViewById(R.id.BackwardSeek);

        preset1 = (Button) findViewById(R.id.Preset1);
        preset2 = (Button) findViewById(R.id.Preset2);
        preset3 = (Button) findViewById(R.id.Preset3);
        preset4 = (Button) findViewById(R.id.Preset4);
        preset5 = (Button) findViewById(R.id.Preset5);
        preset6 = (Button) findViewById(R.id.Preset6);

        togglePower.setOnClickListener(this);
        AM.setOnClickListener(this);
        FM.setOnClickListener(this);
        scanBack.setOnClickListener(this);
        scanForward.setOnClickListener(this);
        preset1.setOnClickListener(this);
        preset2.setOnClickListener(this);
        preset3.setOnClickListener(this);
        preset4.setOnClickListener(this);
        preset5.setOnClickListener(this);
        preset6.setOnClickListener(this);

        preset1.setOnLongClickListener(this);
        preset2.setOnLongClickListener(this);
        preset3.setOnLongClickListener(this);
        preset4.setOnLongClickListener(this);
        preset5.setOnLongClickListener(this);
        preset6.setOnLongClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if(togglePower.isChecked())
        {
            if(preset1.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[0];
                }
                else
                {
                    FMStation = FMPreset[0];
                    FMDec = FMDecPreset[0];
                }
            }
            if(preset2.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[1];
                }
                else
                {
                    FMStation = FMPreset[1];
                    FMDec = FMDecPreset[1];
                }
            }
            if(preset3.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[2];
                }
                else
                {
                    FMStation = FMPreset[2];
                    FMDec = FMDecPreset[2];
                }
            }
            if(preset4.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[3];
                }
                else
                {
                    FMStation = FMPreset[3];
                    FMDec = FMDecPreset[3];
                }
            }
            if(preset5.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[4];
                }
                else
                {
                    FMStation = FMPreset[4];
                    FMDec = FMDecPreset[4];
                }
            }
            if(preset6.isPressed())
            {
                if(AMFM)
                {
                    AMStation = AMPreset[5];
                }
                else
                {
                    FMStation = FMPreset[5];
                    FMDec = FMDecPreset[5];
                }
            }

            updateDisplay();
        }


        if (AM.isPressed()) {
            AMFM = true;
            updateDisplay();
        }

        if (FM.isPressed()) {
            AMFM = false;
            updateDisplay();
        }

        if (scanForward.isPressed()) {
            if (AMFM == true) {
                AMStation += AMInc;
                if (AMStation > 1700) {
                    AMStation = 530;
                }
            } else {
                FMDec += FMInc;
                if(FMDec > 10)
                {
                    FMDec = 1;
                    FMStation++;
                }
                if (FMStation >= 108) {
                    FMStation = 88;
                    FMDec = 1;
                }
            }
            updateDisplay();
        }

        if (scanBack.isPressed()) {
            if (AMFM == true) {
                AMStation -= AMInc;
                if (AMStation < 530) {
                    AMStation = 1700;
                }

            } else {
                FMDec -= FMInc;
                if(FMDec < 0)
                {
                    FMDec = 9;
                    FMStation--;
                }
                if (FMStation < 88) {
                    FMStation = 107;
                    FMDec = 9;
                }

            }
            updateDisplay();
        }


        if (togglePower.isPressed()) {
            if (!togglePower.isChecked()) {
                previous.setBackgroundColor(0x000000);
                rewind.setBackgroundColor(0x000000);
                playPause.setBackgroundColor(0x000000);
                fastForward.setBackgroundColor(0x000000);
                skipForward.setBackgroundColor(0x000000);
                AM.setBackgroundColor(0xffffffff);
                FM.setBackgroundColor(0xffffffff);
                scanBack.setBackgroundColor(0xffffffff);
                scanForward.setBackgroundColor(0xffffffff);
                text.setTextColor(0xffffffff);
            }
            else {
                previous.setBackgroundColor(0xffffffff);
                rewind.setBackgroundColor(0xffffffff);
                playPause.setBackgroundColor(0xffffffff);
                fastForward.setBackgroundColor(0xffffffff);
                skipForward.setBackgroundColor(0xffffffff);
                AM.setBackgroundColor(0x88000000);
                FM.setBackgroundColor(0x88000000);
                scanForward.setBackgroundColor(0x88000000);
                scanBack.setBackgroundColor(0x88000000);
                text.setTextColor(0xff70a2ff);
            }


        }
    }

    public void updateDisplay()
    {
        if(AMFM)
        {
            text.setText(AMStation + " AM" + "\n5:30");
        }
        else
        {
            text.setText(FMStation + "." + FMDec + " FM" + "\n5:30");
        }
    }

    @Override
    public boolean onLongClick(View v) {

        if(preset1.isPressed())
        {
            if(AMFM)
            {
                AMPreset[0] = AMStation;
            }
            else
            {
                FMPreset[0] = FMStation;
                FMDecPreset[0] = FMDec;
            }
        }
        if(preset2.isPressed())
        {
            if(AMFM)
            {
                AMPreset[1] = AMStation;
            }
            else
            {
                FMPreset[1] = FMStation;
                FMDecPreset[1] = FMDec;
            }
        }
        if(preset3.isPressed())
        {
            if(AMFM)
            {
                AMPreset[2] = AMStation;
            }
            else
            {
                FMPreset[2] = FMStation;
                FMDecPreset[2] = FMDec;
            }
        }
        if(preset4.isPressed())
        {
            if(AMFM)
            {
                AMPreset[3] = AMStation;
            }
            else
            {
                FMPreset[3] = FMStation;
                FMDecPreset[3] = FMDec;
            }
        }
        if(preset5.isPressed())
        {
            if(AMFM)
            {
                AMPreset[4] = AMStation;
            }
            else
            {
                FMPreset[4] = FMStation;
                FMDecPreset[4] = FMDec;
            }
        }
        if(preset6.isPressed())
        {
            if(AMFM)
            {
                AMPreset[5] = AMStation;
            }
            else
            {
                FMPreset[5] = FMStation;
                FMDecPreset[5] = FMDec;
            }
        }

        return false;
    }
}
