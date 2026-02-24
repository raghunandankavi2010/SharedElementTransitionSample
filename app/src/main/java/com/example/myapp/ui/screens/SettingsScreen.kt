package com.example.myapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Appearance Section
        SettingsSectionHeader(title = "Appearance")

        var darkMode by remember { mutableStateOf(false) }
        SettingsSwitchItem(
            icon = Icons.Default.DarkMode,
            title = "Dark Mode",
            subtitle = "Use dark theme",
            checked = darkMode,
            onCheckedChange = { darkMode = it }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        // Notifications Section
        SettingsSectionHeader(title = "Notifications")

        var notifications by remember { mutableStateOf(true) }
        SettingsSwitchItem(
            icon = Icons.Default.Notifications,
            title = "Push Notifications",
            subtitle = "Receive updates and alerts",
            checked = notifications,
            onCheckedChange = { notifications = it }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        // General Section
        SettingsSectionHeader(title = "General")

        SettingsClickableItem(
            icon = Icons.Default.Language,
            title = "Language",
            subtitle = "English (US)",
            onClick = { /* Handle language */ }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsClickableItem(
            icon = Icons.Default.Security,
            title = "Privacy & Security",
            subtitle = "Manage your privacy settings",
            onClick = { /* Handle privacy */ }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        // About Section
        SettingsSectionHeader(title = "About")

        SettingsClickableItem(
            icon = Icons.Default.Info,
            title = "About App",
            subtitle = "Version 1.0.0",
            onClick = { /* Handle about */ }
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
private fun SettingsSwitchItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingContent = {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    )
}

@Composable
private fun SettingsClickableItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingContent = {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = Modifier.clickable(onClick = onClick)
    )
}