# VisiblePositionChangeListener

This is a Recyclerview listener that returns the location of the view when the view at a specific location is visible or no longer visible while using the recycler view.
## How to use
        visiblePositionListener = new VisiblePositionChangeListener(layoutManager,
                new VisiblePositionChangeListener.OnChangeListener() {
                    @Override
                    public void onFirstVisiblePositionChanged(int position) {
                        //Called when the position of the first item is changed.
                    }

                    @Override
                    public void onLastVisiblePositionChanged(int position) {
                        //Called when the position of the last item changes.
                    }

                    @Override
                    public void onFirstInvisiblePositionChanged(int position) {
                        //Called when the first item is no longer visible.
                    }

                    @Override
                    public void onLastInvisiblePositionChanged(int position) {
                        //Called when the last item is no longer visible.
                    }
                }
        );
