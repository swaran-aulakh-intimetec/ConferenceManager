package com.intimetec.conferencemanager.ui.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.intimetec.conferencemanager.R;
import com.intimetec.conferencemanager.data.ConferenceRoomsDataSource;
import com.intimetec.conferencemanager.data.remote.ConferenceRoomsRemoteDataSource;
import com.intimetec.conferencemanager.model.conference.ConferenceRooms;
import com.intimetec.conferencemanager.ui.BaseActivity;
import com.intimetec.conferencemanager.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HomeFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    @NonNull
    private ConferenceRoomsDataSource mConferenceRoomsDataSource;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conferencerooms_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.conference_rooms_list);
        mConferenceRoomsDataSource = checkNotNull(ConferenceRoomsRemoteDataSource.getInstance(), "ConferenceRoomsDataSource cannot be null");
        mCompositeDisposable = new CompositeDisposable();

        mListener = (OnListFragmentInteractionListener) getActivity();

        getConferenceRooms();

        return view;
    }

    private void getConferenceRooms() {
        mCompositeDisposable.clear();
        ((BaseActivity) getActivity()).showProgressDialog();

        mCompositeDisposable.add(mConferenceRoomsDataSource
                .getConferenceRooms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        // onNext
                        this::onGetConferenceRooms,
                        // onError
                        this::onRequestFailed,
                        // onCompleted
                        () -> ((BaseActivity) getActivity()).hideProgressDialog()));
    }

    private void onGetConferenceRooms(ConferenceRooms conferenceRooms) {
        ((BaseActivity) getActivity()).hideProgressDialog();
        setAdapter(conferenceRooms.getData());
    }

    private void onRequestFailed(Throwable throwable) {
        ((BaseActivity) getActivity()).hideProgressDialog();
        Toast.makeText(getActivity(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    private void setAdapter(List<ConferenceRooms.Data> conferenceRoomsList) {
        // Set the adapter

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(new ConferenceRoomsRecyclerViewAdapter(conferenceRoomsList, mListener));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ConferenceRooms.Data item);
    }
}
