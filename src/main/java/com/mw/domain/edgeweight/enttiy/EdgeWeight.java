package com.mw.domain.edgeweight.enttiy;

import com.mw.domain.edge.entity.Edge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EdgeWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Edge edge;

    @Enumerated(EnumType.STRING)
    private WeightCode weightCode;

    private int weightValue;

    @Builder
    public EdgeWeight(EdgeWeightDto edgeWeightDto) {
        this.weightCode = edgeWeightDto.getWeightCode();
        this.weightValue = edgeWeightDto.getWeightValue();
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}