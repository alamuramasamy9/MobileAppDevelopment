package edu.northeastern.numadsp23_alamuramasamy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PrimeNumberView extends AppCompatActivity {

    private Handler textViewHandler;
    private TextView primeNumber;
    private TextView currentNumber;
    private Thread thread;
    private FindPrime findPrime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_number_view);

        textViewHandler = new Handler();
        Button primeSearch = findViewById(R.id.primeSearch);
        Button terminateSearch = findViewById(R.id.terminateSearch);
        primeNumber = findViewById(R.id.currentPrime);
        currentNumber = findViewById(R.id.currentNumber);

        terminateSearch.setEnabled(false);
        primeSearch.setOnClickListener(view -> {
            findPrime = new FindPrime();
            thread = new Thread(findPrime);
            thread.start();
            primeSearch.setEnabled(false);
            terminateSearch.setEnabled(true);
        });
        terminateSearch.setOnClickListener(view -> {
            if (thread != null && findPrime != null) {
                findPrime.stop();
                primeSearch.setEnabled(true);
                terminateSearch.setEnabled(false);
            }

        });


    }
    @Override
    public void onBackPressed() {
        Button findPrimeBtn = findViewById(R.id.primeSearch);
        Button terminateBtn = findViewById(R.id.terminateSearch);
        new AlertDialog.Builder(this)
                .setTitle("Terminate Search")
                .setMessage("Are you sure you want to terminate the search?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        if (thread != null && findPrime != null) {
                            findPrime.stop();
                            findPrimeBtn.setEnabled(true);
                            terminateBtn.setEnabled(false);
                            finish();
                        }
                    }
                })

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    class FindPrime implements Runnable {

        private volatile boolean mIsStopped = false;

        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            setStopped(false);
            int num = 3;

            while (!mIsStopped) {
                final int output = num;
                if (checkPrime(num)) {
                    textViewHandler.post(() -> primeNumber.setText("" + output));
                }
                textViewHandler.post(() -> currentNumber.setText("" + output));
                num += 2;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean checkPrime(int num) {
            boolean flag = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if ((num % i) == 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        private void setStopped(boolean isStop) {
            if (mIsStopped != isStop)
                mIsStopped = isStop;
        }

        public void stop() {
            setStopped(true);
        }
    }
}
