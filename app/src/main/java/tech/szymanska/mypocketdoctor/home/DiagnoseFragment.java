package tech.szymanska.mypocketdoctor.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tech.szymanska.mypocketdoctor.R;

public class DiagnoseFragment extends Fragment {

    private Animation slide_out_message_box;
    private LinearLayout inputLayout;
    private LinearLayout chatLayout;
    private Animate animate = new Animate();
    private View view;

    public DiagnoseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        chatLayout = view.findViewById(R.id.chatLayout);
        chatLayout.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                DiagnoseFragment.this.scrollChat();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
            }
        });
        this.view = view;
        this.setFirstMessage();
        this.animate = new Animate();
        slide_out_message_box = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_messbox);
        this.inputLayout = view.findViewById(R.id.inputLayout);
        this.setSendMessageButton();
    }

    private void scrollChat() {
        ScrollView scrollViewChat = this.view.findViewById(R.id.scrollViewChat);
        scrollViewChat.post(() -> scrollViewChat.fullScroll(View.FOCUS_DOWN));
    }

    private void setFirstMessage() {
        String firstDoctorMessage =
                getString(R.string.hallo_only)
                        + "Natalia! "
                        + getString(R.string.how_can_i_help_you);
        LinearLayout linearLayout = (LinearLayout) View.inflate(this.getContext(), R.layout.message_doctor,
                null);
        TextView valueTV = linearLayout.findViewById(R.id.doctorMessage);
        valueTV.setText(firstDoctorMessage);
        LinearLayout chatLayout = this.view.findViewById(R.id.chatLayout);
        chatLayout.addView(linearLayout);
    }

    private void setSendMessageButton() {
        Button button = this.view.findViewById(R.id.sendSymptoms);
        button.setOnClickListener(this::onClickSendMessageButton);
    }

    private void onClickSendMessageButton(View view) {
        EditText editText = this.view.findViewById(R.id.inputSymptoms);
        if (editText.getText().toString().trim().length() > 0) {
            this.hideMessageBox();
            createUserMessage(editText);
            createDoctorMessage();
        } else {
            Toast.makeText(this.getContext(), getString(R.string.input_can_not_be_empty),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void createUserMessage(EditText editText) {
        LinearLayout linearLayout = (LinearLayout) View.inflate(this.getContext(), R.layout.message_user,
                null);
        TextView valueTV = linearLayout.findViewById(R.id.userMessage);
        valueTV.setText(editText.getText().toString());
        chatLayout.addView(linearLayout);
    }

    private void createDoctorMessage() {
        LinearLayout linearLayout = (LinearLayout) View.inflate(this.getContext(), R.layout.message_doctor,
                null);
        TextView valueTV = linearLayout.findViewById(R.id.doctorMessage);
        valueTV.setText("Do you have a runny nose?");
        this.chatLayout.addView(linearLayout);
        this.addResponseButtons();
    }

    public void addResponseButtons() {
        String[] msg = {"YES", "NO", "DON'T KNOW", "FINISH"};
        this.inputLayout.removeAllViews();
        this.inputLayout.setAnimation(AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_buttons));
        for (int i = 0; i < msg.length; i++) {
            Button button = (Button) View.inflate(this.getContext(), R.layout.answer_button, null);
            button.setText(msg[i]);
            this.inputLayout.addView(button);
            Space space = new Space(this.getContext());
            space.setLayoutParams(new LinearLayout.LayoutParams(12, 8));
            this.inputLayout.addView(space);
        }
    }

    public void hideMessageBox() {
        this.animate.hideMessageBox();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diagnose, container, false);
    }

    private class Animate {

        private boolean isRunning = false;

        public boolean isRunning() {
            return isRunning;
        }

        public void hideMessageBox() {
            slide_out_message_box.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    inputLayout.removeAllViews();

                    Animate.this.isRunning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            this.isRunning = true;
            inputLayout.setAnimation(slide_out_message_box);
        }
    }
}