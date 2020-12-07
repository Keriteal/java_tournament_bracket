package com.tournament.managerment.service;

import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService{
    private final static Logger logger = LoggerFactory.getLogger(TournamentServiceImpl.class);
    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository=tournamentRepository;
    }

    @Override
    public TournamentListDTO getTournamentList() {
        //TournamentListDTO tournamentList = new TournamentListDTO(tournamentRepository.getTournamentIdList(), tournamentRepository.getFormatList());
        TournamentListDTO tournamentList=new TournamentListDTO();
        tournamentList.setTournamentId(tournamentRepository.getTournamentIdList());
        tournamentList.setFormat(tournamentRepository.getFormatList());

        logger.info("get tournament list idList:{}, formatList:{}", tournamentList.getTournamentId(), tournamentList.getFormat());

        return tournamentList;
    }
}
