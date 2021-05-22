import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VisiblePositionChangeListener extends RecyclerView.OnScrollListener {
    public interface OnChangeListener {
        void onFirstVisiblePositionChanged(int position);
        void onLastVisiblePositionChanged(int position);
        void onFirstInvisiblePositionChanged(int position);
        void onLastInvisiblePositionChanged(int position);
    }

    private int firstVisiblePosition;
    private int lastVisiblePosition;
    private final OnChangeListener listener;
    private LinearLayoutManager layoutManager;

    public VisiblePositionChangeListener(LinearLayoutManager linearLayoutManager, OnChangeListener listener) {
        this.listener = listener;
        this.firstVisiblePosition = RecyclerView.NO_POSITION;
        this.lastVisiblePosition = RecyclerView.NO_POSITION;
        this.layoutManager = linearLayoutManager;
    }

    public void refreshVisiblePosition(){
        firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();

        if (firstVisiblePosition == RecyclerView.NO_POSITION || lastVisiblePosition == RecyclerView.NO_POSITION) {
            firstVisiblePosition = firstPosition;
            lastVisiblePosition = lastPosition;
            return;
        }

        if (firstPosition < firstVisiblePosition) {
            if (firstVisiblePosition - firstPosition > 1) {
                for (int i = 1; i < firstVisiblePosition - firstPosition + 1; i++) {
                    listener.onFirstVisiblePositionChanged(firstVisiblePosition - i);
                }
            } else {
                listener.onFirstVisiblePositionChanged(firstPosition);
            }
            firstVisiblePosition = firstPosition;
        } else if (firstPosition > firstVisiblePosition) {
            if (firstPosition - firstVisiblePosition > 1) {
                for (int i = firstPosition - firstVisiblePosition; i > 0; i--) {
                    listener.onFirstInvisiblePositionChanged(firstPosition - i);
                }
            } else {
                listener.onFirstInvisiblePositionChanged(firstVisiblePosition);
            }
            firstVisiblePosition = firstPosition;
        }

        if (lastPosition > lastVisiblePosition) {
            if (lastPosition - lastVisiblePosition > 1) {
                for (int i = 1; i < lastPosition - lastVisiblePosition + 1; i++) {
                    listener.onLastVisiblePositionChanged(lastVisiblePosition + i);
                }
            } else {
                listener.onLastVisiblePositionChanged(lastPosition);
            }
            lastVisiblePosition = lastPosition;
        } else if (lastPosition < lastVisiblePosition) {
            if (lastVisiblePosition - lastPosition > 1) {
                for (int i = 0; i < lastVisiblePosition - lastPosition; i++) {
                    listener.onLastInvisiblePositionChanged(lastVisiblePosition - i);
                }
            } else {
                listener.onLastInvisiblePositionChanged(lastVisiblePosition);
            }
            lastVisiblePosition = lastPosition;
        }
    }
}
