package com.example.eventservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"chat_id", "event_id"})})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @ManyToOne
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk_subscription_event"))
    private Event event;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
