package com.mw.domain.pathfind;

import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.node.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private EdgeRepository edgeRepository;

    @Test
    public void findAllTest() {
        System.out.println(nodeRepository.findAll().size());
    }

    @Test
    public void findAllEdgeTest() {
        System.out.println(edgeRepository.findAll().size());
    }
}
