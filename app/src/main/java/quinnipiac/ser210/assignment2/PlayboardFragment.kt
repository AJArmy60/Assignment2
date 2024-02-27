/*
Playboard Fragment
The gameboard that the user can click/tap on to change the squares blue.
@author aarmellino
@date 2/25/2024
 */

package quinnipiac.ser210.assignment2

import FourInARow2
import GameConstants
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
lateinit var fourRow:FourInARow2
//var currentTurn: Int = GameConstants.BLUE

/**
 * A simple [Fragment] subclass.
 * Use the [PlayboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fourRow = FourInARow2()

        val fourRowGrid = view.findViewById<androidx.gridlayout.widget.GridLayout>(R.id.playgrid)

        for (index in 0 until fourRowGrid.childCount) {
            var cell = fourRowGrid.getChildAt(index)
            //cell.setBackgroundResource(R.drawable.empty_cell)
            cell.setOnClickListener {

                if (fourRow.getCell(index) == GameConstants.EMPTY) {
                    cell.setBackgroundResource(R.drawable.blue_cell)
                    gameloop(index,cell)
                }
            }
        }
    }

    private fun gameloop(playerLocation: Int, view: View)
    {

        fourRow.setMove(GameConstants.BLUE, playerLocation)

        val compMove = fourRow.computerMove

        //fourRow.checkForWinner()
    }

}