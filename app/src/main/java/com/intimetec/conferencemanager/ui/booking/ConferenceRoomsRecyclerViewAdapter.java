package com.intimetec.conferencemanager.ui.booking;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intimetec.conferencemanager.R;
import com.intimetec.conferencemanager.model.conference.ConferenceRooms;
import com.intimetec.conferencemanager.ui.booking.HomeFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ConferenceRooms.Data} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ConferenceRoomsRecyclerViewAdapter extends RecyclerView.Adapter<ConferenceRoomsRecyclerViewAdapter.ViewHolder> {

    private final List<ConferenceRooms.Data> mConferenceRooms;
    private final OnListFragmentInteractionListener mListener;

    public ConferenceRoomsRecyclerViewAdapter(List<ConferenceRooms.Data> items, OnListFragmentInteractionListener listener) {
        mConferenceRooms = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conferencerooms, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mConferenceRooms.get(position);
        holder.mConfRoomName.setText(holder.mItem.getName());
        holder.mConfRoomLocation.setText(holder.mItem.getLocation());
        holder.mConfRoomMaxParticipants.setText("Max Participants : " + holder.mItem.getMaxParticipants());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mConferenceRooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mConfRoomName;
        public final TextView mConfRoomLocation;
        public final TextView mConfRoomMaxParticipants;
        public ConferenceRooms.Data mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mConfRoomName = (TextView) view.findViewById(R.id.conf_room_name);
            mConfRoomLocation = (TextView) view.findViewById(R.id.conf_room_location);
            mConfRoomMaxParticipants = (TextView) view.findViewById(R.id.conf_room_max_participants);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mConfRoomLocation.getText() + "'";
        }
    }
}
