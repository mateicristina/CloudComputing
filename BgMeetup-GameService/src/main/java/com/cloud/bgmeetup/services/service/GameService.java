package com.cloud.bgmeetup.services.service;

import com.cloud.bgmeetup.services.dto.GameDto;
import com.cloud.bgmeetup.services.exceptions.EntityNotFoundException;
import com.cloud.bgmeetup.services.mapper.GameMapper;
import com.cloud.bgmeetup.services.model.Game;
import com.cloud.bgmeetup.services.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameMapper gameMapper, GameRepository gameRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    public GameDto get(String id) {
        return gameRepository.get(id).orElseThrow(()-> new EntityNotFoundException("Game"));
    }

    public List<GameDto> getAll() {
        return gameRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(GameDto request) {
        Game game = gameMapper.toEntity(request);
        gameRepository.save(game);
    }

    public void update(GameDto request) {
        Game game = gameMapper.toEntity(request);
        gameRepository.update(game);
    }

    public void delete(String id) {
        gameRepository.delete(id);
    }
}
